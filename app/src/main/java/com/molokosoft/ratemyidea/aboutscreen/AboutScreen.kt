package com.molokosoft.ratemyidea.aboutscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.molokosoft.ratemyidea.R

@Composable
fun AboutScreen (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    onShowPrivacyPolicy: () -> Unit,
    onShowTermsOfService: () -> Unit
) {
    val typography = LocalAppTypography.current
    val colors = LocalColorScheme.current

    val verticalScroll = rememberScrollState()

    Box (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp)
            .background (
                color = PostIdeaYellow,
                shape = RoundedCornerShape(12.dp)
            )
            .border (
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = colors.tertiary
            )
            .clip (
                RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.TopCenter
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
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(verticalScroll)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer (
                modifier = Modifier
                    .height(32.dp)
            )

            Text (
                text = "RateMyIdea is a platform designed to help turn raw ideas into validated opportunities.\n" +
                        "\n" +
                        "Have a business idea but not sure if it’s worth pursuing? Share it with the community and get honest, real-time feedback from other users. Whether you're an aspiring entrepreneur, a creative thinker, or just curious, RateMyIdea gives you a space to test concepts before investing time and money.\n" +
                        "\n" +
                        "Users can post their ideas, and others can rate and evaluate them based on potential, originality, and viability. This crowd-based validation helps you quickly understand how your idea is perceived and whether or not you should pursue it.\n" +
                        "\n" ,
                fontWeight = typography.titleMedium.fontWeight,
                fontSize = typography.titleMedium.fontSize,
                textAlign = TextAlign.Center
            )

            Text (
                text = "Our mission is simple:\n",
                fontWeight = typography.titleMedium.fontWeight,
                fontSize = typography.titleMedium.fontSize,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )

            Text (
                text = "Make idea validation accessible, fast, and collaborative.\n" +
                        "\n" +
                        "Because every great business starts with an idea - but the best ones are tested.",
                fontWeight = typography.titleMedium.fontWeight,
                fontSize = typography.titleMedium.fontSize,
                textAlign = TextAlign.Center
            )

            Spacer (
                modifier = Modifier
                    .height(32.dp)
            )

            Text (
                text = "Privacy Policy",
                fontWeight = typography.titleMedium.fontWeight,
                fontSize = typography.titleMedium.fontSize,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                modifier = Modifier
                    .clickable() {
                        onShowPrivacyPolicy()
                    }
            )

            Spacer (
                modifier = Modifier
                    .height(4.dp)
            )

            Text (
                text = "Terms of Service",
                fontWeight = typography.titleMedium.fontWeight,
                fontSize = typography.titleMedium.fontSize,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                color = Color.Black,
                modifier = Modifier
                    .clickable() {
                        onShowTermsOfService()
                    }
            )

            Spacer (
                modifier = Modifier
                    .height(16.dp)
            )
        }
    }
}