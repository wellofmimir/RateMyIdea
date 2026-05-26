package com.molokosoft.ratemyidea.ideasscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow
import com.molokosoft.ratemyidea.R
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun AddIdeaTile (
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    titleText: String ,
    descriptionText: String,
    onDescriptionChange: (String) -> Unit
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Box (
        modifier = modifier
        .fillMaxSize()
        .border (
            width = 1.dp,
            color = color.tertiary,
            shape = RoundedCornerShape(12.dp)
        )
        .background (
            color = PostIdeaYellow,
            shape = RoundedCornerShape(12.dp)
        )
        .clip (
            RoundedCornerShape(12.dp)
        )
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
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer (
                modifier = Modifier
                    .height(16.dp)
            )

            Text (
                text = "What is your idea?",
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center
            )

            Spacer (
                modifier = Modifier
                    .height(32.dp)
            )

            Text (
                text = "Give it a title",
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight,
                textAlign = TextAlign.Center
            )

            OutlinedTextField (
                value = titleText,
                label = {
                    Text (
                        text = "${titleText.length}/50"
                    )
                },
                onValueChange = {
                    if (it.length <= 50) {
                        onTitleChange(it)
                    }
                },
                textStyle = TextStyle (
                    textAlign = TextAlign.Start,
                    fontSize = typography.bodyLarge.fontSize,
                    fontWeight = typography.bodyLarge.fontWeight,
                ),
                colors = OutlinedTextFieldDefaults.colors (
                    focusedContainerColor = color.background,
                    unfocusedContainerColor = color.background,

                    focusedBorderColor = color.tertiary,
                    unfocusedBorderColor = color.tertiary.copy(alpha = 0.5f),

                    cursorColor = color.tertiary,
                    focusedTextColor = color.tertiary,
                    unfocusedTextColor = color.tertiary
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer (
                modifier = Modifier
                    .height(16.dp)
            )

            Text (
                text = "Describe your idea",
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight,
                textAlign = TextAlign.Center
            )


            OutlinedTextField (
                value = descriptionText,
                label = {
                    Text (
                        text = "${descriptionText.length}/350"
                    )
                },
                onValueChange = {
                    if (it.length <= 350) {
                        onDescriptionChange(it)
                    }
                },
                textStyle = TextStyle (
                    textAlign = TextAlign.Start,
                    fontSize = typography.bodyLarge.fontSize,
                    fontWeight = typography.bodyLarge.fontWeight,
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .weight(1f),
                colors = OutlinedTextFieldDefaults.colors (
                    focusedContainerColor = color.background,
                    unfocusedContainerColor = color.background,

                    focusedBorderColor = color.tertiary,
                    unfocusedBorderColor = color.tertiary.copy(alpha = 0.5f),

                    cursorColor = color.tertiary,
                    focusedTextColor = color.tertiary,
                    unfocusedTextColor = color.tertiary
                )
            )

            Spacer (
                modifier = Modifier
                    .height(32.dp)
            )
        }
    }


}