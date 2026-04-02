package com.example.ratemyidea.addideascreen

import android.app.Activity
import com.example.ratemyidea.network.model.Idea

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.ratemyidea.ideasscreen.AddIdeaTile
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalContext
import com.example.ratemyidea.common.DecisionDialog
import com.example.ratemyidea.common.ErrorMessageDialog
import com.example.ratemyidea.ui.theme.LocalAppTypography
import com.example.ratemyidea.ui.theme.LocalColorScheme
import com.example.ratemyidea.ui.theme.PostIdeaYellow

@Composable
fun AddIdeaScreen (
    modifier: Modifier = Modifier,
    paddingValues: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(),
    addIdeaScreenViewModel: IdeaViewModel
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val context = LocalContext.current

    var titleText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }
    var resetTrigger by remember { mutableIntStateOf(0) }
    var showError by remember { mutableStateOf(false) }
    var showInformationForPosting by remember { mutableStateOf(false) }
    var showPaymentProposal by remember { mutableStateOf(false) }
    var uploadUnlocked by remember { mutableStateOf(false) }

    val activity = context as? Activity

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showError) {
            ErrorMessageDialog (
                titleText = "An error has occurred.",
                descriptionText = "Please enter a title and description.",
                onDismissRequest = {
                    showError = false
                }
            )
        }

        if (showInformationForPosting) {
            DecisionDialog (
                titleText = "You're almost there!",
                descriptionText = "Rate 50 ideas to unlock posting your own — or skip ahead for just $1.99.",
                confirmTextButton = "Skip the line",
                dismissTextButton = "Cancel",
                onConfirmRequest = {
                    showInformationForPosting = false
                    showPaymentProposal = true
                },
                onDismissRequest = {
                    showInformationForPosting = false
                }
            )
        }


        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        AddIdeaTile (
            modifier = Modifier
                .weight(1f),
            resetTrigger = resetTrigger,
            onTitleChange = {
                titleText = it
            },
            onDescriptionChange = {
                descriptionText = it
            }
        )

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background (
                    color = PostIdeaYellow,
                    shape = RoundedCornerShape(24.dp)
                )
                .border (
                    width = 1.dp,
                    shape = RoundedCornerShape(24.dp),
                    color = colors.tertiary
                )
                .clickable (
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (titleText.isBlank() || descriptionText.isBlank()) {
                        showError = true
                        return@clickable
                    }

                    if (addIdeaScreenViewModel.ideasRated() < 50) {
                        showInformationForPosting = true
                        return@clickable
                    } else if (addIdeaScreenViewModel.ideasRated() >= 50) {
                        addIdeaScreenViewModel.onWatchAd(activity!!)
                    } else if (showPaymentProposal) {
                        TODO("Payment processing einbauen")
                        uploadUnlocked = true
                    }

                    if (uploadUnlocked) {
                        val idea = Idea("", titleText, descriptionText)
                        addIdeaScreenViewModel.updateIdea(idea)
                        resetTrigger++
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text (
                text = "Rate My Idea",
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight
            )
        }

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )
    }
}