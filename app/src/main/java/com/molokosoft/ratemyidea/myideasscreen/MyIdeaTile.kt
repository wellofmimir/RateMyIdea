package com.molokosoft.ratemyidea.myideasscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import com.molokosoft.ratemyidea.network.model.Idea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import androidx.compose.foundation.shape.RoundedCornerShape
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.molokosoft.ratemyidea.R
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun MyIdeaTile (
    modifier: Modifier = Modifier,
    idea: Idea,
    onIdeaClick: (idea: Idea) -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .border (
                width = 1.dp,
                color = colors.tertiary,
                shape = RoundedCornerShape(12.dp)
            )
            .clip (
                RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onIdeaClick(idea)
            }
    ) {
        Image (
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box (
            modifier = Modifier
                .matchParentSize()
                .background(PostIdeaYellow.copy(alpha = 0.4f))
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 24.dp)
        ) {
            Text (
                text = idea.title,
                fontSize = typography.bodyLarge.fontSize,
                fontWeight = typography.bodyLarge.fontWeight,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Start,
                color = colors.tertiary,
            )

            Row ( modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End )
            {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.guteidee),
                        contentDescription = "GuteIdee",
                        modifier = Modifier .size(33.dp)
                    )

                    Text (
                        text = idea.likes.toString(),
                        fontSize = typography.bodyLarge.fontSize,
                        fontWeight = typography.bodyLarge.fontWeight
                    )
                }

                Spacer (
                    modifier = Modifier .width(24.dp)
                )

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.schlechteidee),
                        contentDescription = "SchlechteIdee",
                        modifier = Modifier .size(33.dp)
                    )

                    Text (
                        text = idea.dislikes.toString(),
                        fontSize = typography.bodyLarge.fontSize,
                        fontWeight = typography.bodyLarge.fontWeight
                    )
                }

                Spacer (
                    modifier = Modifier
                    .width(24.dp)
                )
            }

            Spacer (
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}