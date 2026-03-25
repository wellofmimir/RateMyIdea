package com.example.ratemyidea.ideasscreen

import androidx.compose.foundation.Image
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

@Composable
fun IdeaScreen (
    modifier: Modifier = Modifier,
    paddingValues: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues()
) {
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
                .weight(1f)
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
            )
        }

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )
    }
}