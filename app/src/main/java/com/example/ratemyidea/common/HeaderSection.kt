package com.example.ratemyidea.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import com.example.ratemyidea.R
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.ratemyidea.ui.theme.LocalAppTypography
import com.example.ratemyidea.ui.theme.LocalColorScheme

@Composable
fun HeaderSection (
    modifier: Modifier = Modifier
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Row (
        modifier = modifier
            .fillMaxWidth()
            .background (
                color = color.background
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer (
            modifier = Modifier
                .width(16.dp)
        )

        Image (
            painter = painterResource(id = R.drawable.rate_my_idea_logo),
            contentDescription = "VinoriaLogo",
            modifier = Modifier
                .padding(bottom = 12.dp)
                .size(70.dp)
        )

        Spacer (
            modifier = Modifier
                .width(16.dp)
        )

        Text (
            text = "RateMyIdea",
            fontSize = typography.titleLarge.fontSize,
            fontWeight = typography.titleLarge.fontWeight,
            style = typography.titleLarge
        )
    }
}