package com.example.ratemyidea.ideasscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.example.ratemyidea.ui.theme.LocalAppTypography
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ratemyidea.ui.theme.LocalColorScheme
import com.example.ratemyidea.network.Idea

@Composable
fun IdeaTile (
    modifier: Modifier = Modifier,
    idea: Idea
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current

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
                .height(32.dp)
        )

        Text (
            text = idea.title,
            fontSize = typography.labelLarge.fontSize,
            fontWeight = typography.labelLarge.fontWeight,
            textAlign = TextAlign.Center
        )

        Spacer (
            modifier = Modifier
                .height(16.dp)
        )

//        Row (
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            repeat(3) {
//                Box (
//                    modifier = Modifier
//                        .background (
//                            color = color.background,
//                            shape = RoundedCornerShape(24.dp)
//                        )
//                ) {
//                    Text (
//                        text = "  Social  ",
//                        fontSize = typography.labelLarge.fontSize,
//                        fontWeight = typography.labelLarge.fontWeight
//                    )
//                }
//            }
//        }

        Spacer (
            modifier = Modifier
                .height(16.dp)
        )

        Text (
            text = idea.description,
            fontSize = typography.bodyLarge.fontSize,
            fontWeight = typography.bodyLarge.fontWeight,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        Spacer (
            modifier = Modifier
                .height(16.dp)
        )
    }
}