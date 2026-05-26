package com.molokosoft.ratemyidea.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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

@Composable
fun ErrorMessageDialog (
    titleText: String,
    descriptionText: String,
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
                verticalArrangement = Arrangement.SpaceEvenly,
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

                Spacer (
                    modifier = Modifier
                        .height(16.dp)
                )

                TextButton (
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .border (
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background (
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text (
                        text = "Okay",
                        color = colors.tertiary,
                        fontSize = typography.titleMedium.fontSize
                    )
                }
            }
        }
    }
}