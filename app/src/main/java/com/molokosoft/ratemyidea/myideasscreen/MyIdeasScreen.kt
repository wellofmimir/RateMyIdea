package com.molokosoft.ratemyidea.myideasscreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme

import androidx.compose.ui.unit.dp
import com.molokosoft.ratemyidea.network.model.Idea

import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import android.os.Build

import com.molokosoft.ratemyidea.common.DecisionDialog

@Composable
fun MyIdeasScreen (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    myIdeas: List<Idea>,
    alreadyInsertedAnIdea: Boolean,
    deeperInsightsUnlocked: Boolean,
    onInsightsOfferAccepted: () -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val scrollState = rememberScrollState()

    var showMyIdea by remember { mutableStateOf(false) }
    var showInsightsOffer by remember { mutableStateOf(false) }
    var showInsights by remember { mutableStateOf(false) }
    var currentIdea: Idea by remember { mutableStateOf(Idea("", "", "")) }

    LaunchedEffect(deeperInsightsUnlocked) {
        showInsightsOffer = !deeperInsightsUnlocked
    }

    if (myIdeas.isEmpty()) {
        Box (
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text (
                text = if (alreadyInsertedAnIdea) "Loading Your Ideas..." else "Post an idea!",
                color = colors.tertiary,
                fontSize = typography.titleLarge.fontSize
            )
        }

        return
    } else if (showInsightsOffer) {
        DecisionDialog (
            titleText = "Deeper Insights",
            descriptionText = "Analyze your ratings by audience.\nOne purchase. All ideas. Forever.",
            confirmTextButton = "Unlock - $3.99",
            dismissTextButton = "No, Thanks",
            onConfirmRequest = {
                onInsightsOfferAccepted()
            },
            onDismissRequest = {
                showInsightsOffer = false
            }
        )
    }

    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp)
            .background (
                color = Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable (
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
            }
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showMyIdea) {
            MyIdeaDialog (
                deeperInsightsUnlocked = deeperInsightsUnlocked,
                currentIdea = currentIdea,
                onShowInsights = {
                    showInsights = true
                    showMyIdea = false
                },
                onDismissRequest = {
                    showMyIdea = false
                    showInsights = false
                }
            )
        } else if (showInsights) {
            InsightsDialog (
                idea = currentIdea,
                onDismissRequest = {
                    showInsights = false
                    showMyIdea = true
                }
            )
        }

        myIdeas.forEach {
            MyIdeaTile (
                modifier = Modifier,
                idea = it,
                onIdeaClick = { it ->
                    currentIdea = it
                    showMyIdea = true
                }
            )

            Spacer (
                modifier = Modifier
                    .height(4.dp)
            )
        }
    }
}