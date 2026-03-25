package com.example.ratemyidea.addideascreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ratemyidea.R
import com.example.ratemyidea.ideasscreen.AddIdeaTile
import com.example.ratemyidea.ideasscreen.IdeaTile
import com.example.ratemyidea.ui.theme.LocalAppTypography
import com.example.ratemyidea.ui.theme.LocalColorScheme
import com.example.ratemyidea.ui.theme.PostIdeaYellow

@Composable
fun AddIdeaScreen (
    modifier: Modifier = Modifier,
    paddingValues: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues()
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        AddIdeaTile (
            modifier = Modifier
                .weight(1f)
        )

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background (
                    color = PostIdeaYellow,
                    shape = RoundedCornerShape(24.dp)
                )
                .border (
                    width = 1.dp,
                    shape = RoundedCornerShape(24.dp),
                    color = color.tertiary
                ),
            contentAlignment = Alignment.Center
        ) {
            Text (
                text = "Rate My Idea",
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight

            )
        }

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )
    }
}