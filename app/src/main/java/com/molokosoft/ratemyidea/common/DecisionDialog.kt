package com.molokosoft.ratemyidea.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

import com.molokosoft.ratemyidea.R
import com.molokosoft.ratemyidea.ui.theme.ConversionBlue
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow
import com.molokosoft.ratemyidea.ui.theme.ConversionGreen

@Composable
fun DecisionDialog (
    titleText: String,
    descriptionText: String,
    confirmTextButton: String,
    dismissTextButton: String,
    onConfirmRequest: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current

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
                .heightIn(225.dp, 275.dp)
                .clickable () {
                    onDismissRequest()
                }
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
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer (
                    modifier = Modifier
                        .height(32.dp)
                )

                Text (
                    text = titleText,
                    color = colors.tertiary,
                    fontSize = typography.labelLarge.fontSize,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )

                Spacer (
                    modifier = Modifier
                        .height(16.dp)
                )

                Text (
                    text = descriptionText,
                    color = colors.tertiary,
                    fontSize = typography.labelMedium.fontSize,
                    textAlign = TextAlign.Center
                )

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    TextButton (
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .border (
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background (
                                color = ConversionGreen,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        onClick = {
                            onConfirmRequest()
                        }
                    ) {
                        Text (
                            text = confirmTextButton,
                            color = Color.White,
                            fontSize = typography.labelLarge.fontSize
                        )
                    }

                    Spacer (
                        modifier = Modifier
                            .height(16.dp)
                    )

                    Text (
                        text = dismissTextButton,
                        color = colors.tertiary,
                        fontSize = typography.labelSmall.fontSize,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable() {
                                onDismissRequest()
                            }
                    )
                }
            }
        }
    }
}