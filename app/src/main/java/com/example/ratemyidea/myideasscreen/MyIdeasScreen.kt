package com.example.ratemyidea.myideasscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ratemyidea.addideascreen.IdeaViewModel


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.ratemyidea.ui.theme.LocalColorScheme

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ratemyidea.network.model.Idea
import android.app.Activity

import com.example.ratemyidea.ui.theme.LocalAppTypography
import com.example.ratemyidea.ui.theme.PostIdeaYellow

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun MyIdeasScreen (
    modifier: Modifier = Modifier,
    paddingValues: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(),
    myIdeas: List<Idea>
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val scrollState = rememberScrollState()

    var showMyIdea by remember { mutableStateOf(false) }
    var currentIdea: Idea by remember { mutableStateOf(Idea("", "", "")) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .background (
                color = colors.primary,
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
            AlertDialog (
                modifier = Modifier
                    .background (
                        color = PostIdeaYellow,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxHeight(0.5f),
                onDismissRequest = {
                    showMyIdea = false
                },
                title = {
                    Text (
                        text = currentIdea.title,
                        color = colors.tertiary,
                        fontSize = typography.titleMedium.fontSize
                    )
                },
                text = {
                    Text (
                        text = currentIdea.description,
                        color = colors.tertiary,
                        fontSize = typography.titleMedium.fontSize
                    )
                },
                confirmButton = {
                },
                containerColor = PostIdeaYellow
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