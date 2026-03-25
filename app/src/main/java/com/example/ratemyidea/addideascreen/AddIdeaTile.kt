package com.example.ratemyidea.ideasscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.example.ratemyidea.ui.theme.LocalAppTypography
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.ratemyidea.ui.theme.LocalColorScheme

@Composable
fun AddIdeaTile (
    modifier: Modifier = Modifier
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current

    var titleText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .border (
                width = 1.dp,
                color = color.tertiary,
                shape = RoundedCornerShape(12.dp)
            )
            .background (
                color = color.surface,
                shape = RoundedCornerShape(12.dp)
            ),
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
                if (it.length <= 50)
                    titleText = it
            },
            textStyle = androidx.compose.ui.text.TextStyle (
                textAlign = TextAlign.Start,
                fontSize = typography.bodyLarge.fontSize,
                fontWeight = typography.bodyLarge.fontWeight,
            ),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors (
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
                    text = "${descriptionText.length}/250"
                )
            },
            onValueChange = {
                if (it.length <= 250)
                    descriptionText = it
            },
            textStyle = androidx.compose.ui.text.TextStyle (
                textAlign = TextAlign.Start,
                fontSize = typography.bodyLarge.fontSize,
                fontWeight = typography.bodyLarge.fontWeight,
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(1f),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors (
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