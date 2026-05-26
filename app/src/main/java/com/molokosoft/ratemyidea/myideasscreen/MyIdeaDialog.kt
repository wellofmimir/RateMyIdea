package com.molokosoft.ratemyidea.myideasscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.molokosoft.ratemyidea.R
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import com.molokosoft.ratemyidea.network.model.Idea

@Composable
fun MyIdeaDialog (
    deeperInsightsUnlocked: Boolean,
    currentIdea: Idea,
    onDismissRequest: () -> Unit,
    onShowInsights: () -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current

    val verticalScroll = rememberScrollState()

    Dialog (
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Box (
            modifier = Modifier
                .border (
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                )
                .background (
                    color = PostIdeaYellow,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip (
                    RoundedCornerShape(12.dp)
                )
                .fillMaxHeight(0.75f)
        ) {
            Image (
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
            )

            Box (
                modifier = Modifier
                    .matchParentSize()
                    .background (
                        PostIdeaYellow.copy(alpha = 0.4f)
                    )
            )

            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer (
                    modifier = Modifier
                        .height(32.dp)
                )

                Text (
                    text = currentIdea.title,
                    color = colors.tertiary,
                    fontSize = typography.labelLarge.fontSize,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                )

                Spacer (
                    modifier = Modifier
                        .height(16.dp)
                )

                Text (
                    text = currentIdea.description,
                    color = colors.tertiary,
                    fontSize = typography.labelMedium.fontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 24.dp)
                        .verticalScroll(verticalScroll)
                )

                if (deeperInsightsUnlocked) {
                    TextButton (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .heightIn(50.dp, 50.dp)
                            .border (
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background (
                                color = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        onClick = {
                            onShowInsights()
                        }
                    ) {
                        Text (
                            text = "Show Analytics",
                            color = colors.tertiary,
                            fontSize = typography.titleMedium.fontSize
                        )
                    }
                }

                Text (
                    text = "Close",
                    color = colors.tertiary,
                    fontSize = typography.titleMedium.fontSize,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable() {
                            onDismissRequest()
                        }
                )
            }
        }
    }
}