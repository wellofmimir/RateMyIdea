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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.network.model.Idea
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import com.molokosoft.ratemyidea.R

@Composable
fun IdeaTile (
    modifier: Modifier = Modifier,
    idea: Idea?
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val verticalScroll = rememberScrollState()

    Box (
        modifier = modifier
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
    ){
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
                .fillMaxSize()
                .padding(horizontal = 2.dp)
                .verticalScroll(verticalScroll),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer (
                modifier = Modifier
                    .height(32.dp)
            )

            Text (
                text = idea?.title ?: "",
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Spacer (
                modifier = Modifier
                    .height(16.dp)
            )

            Spacer (
                modifier = Modifier
                    .height(16.dp)
            )

            Text (
                text = idea?.description ?: "Loading the next idea...",
                fontSize = typography.bodyLarge.fontSize,
                fontWeight = typography.bodyLarge.fontWeight,
                textAlign = if (idea?.uuid?.isBlank() == true) TextAlign.Center else TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            )

            Spacer (
                modifier = Modifier
                    .height(16.dp)
            )
        }
    }


}