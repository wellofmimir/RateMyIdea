package com.example.ratemyidea.ideasscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ratemyidea.R
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import com.example.ratemyidea.addideascreen.IdeaViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.ratemyidea.ui.theme.LocalAppTypography

@Composable
fun IdeaScreen (
    modifier: Modifier = Modifier,
    paddingValues: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(),
    addIdeaScreenViewModel: IdeaViewModel
) {
    val typography = LocalAppTypography.current
    val currentIdea by addIdeaScreenViewModel.idea.collectAsState()

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

        IdeaTile (
            modifier = Modifier
                .weight(1f),
            idea = currentIdea
        )

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image (
                painter = painterResource(id = R.drawable.guteidee),
                contentDescription = "GuteIdee",
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .size(88.dp)
                    .clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    )  {
                        addIdeaScreenViewModel.rateIdea( true)
                    }
            )

            Spacer (
                modifier = Modifier
                    .width(32.dp)
            )

            Image (
                painter = painterResource(id = R.drawable.schlechteidee),
                contentDescription = "SchlechteIdee",
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .size(88.dp)
                    .clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    )  {
                        addIdeaScreenViewModel.rateIdea(false)
                    }
            )
        }

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )
    }
}
