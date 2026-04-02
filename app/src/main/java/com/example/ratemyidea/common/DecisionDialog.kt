package com.example.ratemyidea.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ratemyidea.ui.theme.LocalAppTypography
import com.example.ratemyidea.ui.theme.LocalColorScheme
import com.example.ratemyidea.ui.theme.PostIdeaYellow

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

    AlertDialog (
        modifier = Modifier
            .background (
                color = PostIdeaYellow,
                shape = RoundedCornerShape(12.dp)
            )
            .height(250.dp),
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            Text (
                text = titleText,
                color = colors.tertiary,
                fontSize = typography.titleMedium.fontSize
            )
        },
        text = {
            Text (
                text = descriptionText,
                color = colors.tertiary,
                fontSize = typography.titleMedium.fontSize
            )
        },
        confirmButton = {
            TextButton (
                modifier = Modifier
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
                    onConfirmRequest()
                }
            ) {
                Text (
                    text = confirmTextButton,
                    color = colors.tertiary,
                    fontSize = typography.titleMedium.fontSize
                )
            }
        },
        dismissButton = {
            TextButton (
                modifier = Modifier
                    .border (
                        width = 1.dp,
                        color = PostIdeaYellow,
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
                    text = dismissTextButton,
                    color = colors.tertiary,
                    fontSize = typography.titleMedium.fontSize
                )
            }
        },
        containerColor = PostIdeaYellow
    )
}