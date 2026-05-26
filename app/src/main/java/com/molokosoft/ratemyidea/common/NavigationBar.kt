package com.molokosoft.ratemyidea.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import androidx.compose.ui.graphics.Color
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.molokosoft.ratemyidea.R
import com.molokosoft.ratemyidea.ui.theme.ConversionGreen
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow

@Composable
fun NavigationBarSection (
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val color = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Column {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp) // Höhe anpassen
                .background(Color.Black)
        )

        NavigationBar (
            containerColor = PostIdeaYellow.copy(alpha = 0.4f),
        ) {
            NavigationBarItem (
                selected = selectedIndex == 0,
                onClick = { onItemSelected(0) },
                icon = {
                    Icon(Icons.Default.List, contentDescription = "Ideas")
                },
                label = {
                    Text (
                        text = "Ideas",
                        fontSize = typography.labelSmall.fontSize
                    )
                },
                colors = NavigationBarItemDefaults.colors (
                    selectedIconColor = color.tertiary,
                    selectedTextColor = color.tertiary,
                    indicatorColor = color.onBackground,
                    unselectedIconColor = color.tertiary,
                    unselectedTextColor = color.tertiary
                )
            )

            NavigationBarItem (
                selected = selectedIndex == 1,
                onClick = { onItemSelected(1) },
                icon = {
                    Icon (Icons.Default.Add, contentDescription = "Post")
                },
                label = {
                    Text (
                        text = "Post",
                        fontSize = typography.labelSmall.fontSize
                    )
                },
                colors = NavigationBarItemDefaults.colors (
                    selectedIconColor = color.tertiary,
                    selectedTextColor = color.tertiary,
                    indicatorColor = color.onBackground,
                    unselectedIconColor = color.tertiary,
                    unselectedTextColor = color.tertiary
                )
            )

            NavigationBarItem (
                selected = selectedIndex == 2,
                onClick = { onItemSelected(2) },
                icon = {
                    Icon(Icons.Default.ThumbUp, contentDescription = "Ratings")
                },
                label = {
                    Text (
                        text = "Ratings",
                        fontSize = typography.labelSmall.fontSize
                    )
                },
                colors = NavigationBarItemDefaults.colors (
                    selectedIconColor = color.tertiary,
                    selectedTextColor = color.tertiary,
                    indicatorColor = color.onBackground,
                    unselectedIconColor = color.tertiary,
                    unselectedTextColor = color.tertiary
                )
            )

            NavigationBarItem (
                selected = selectedIndex == 3,
                onClick = { onItemSelected(3) },
                icon = {
                    Icon(Icons.Default.Info, contentDescription = "About")
                },
                label = {
                    Text (
                        text = "About",
                        fontSize = typography.labelSmall.fontSize
                    )
                },
                colors = NavigationBarItemDefaults.colors (
                    selectedIconColor = color.tertiary,
                    selectedTextColor = color.tertiary,
                    indicatorColor = color.onBackground,
                    unselectedIconColor = color.tertiary,
                    unselectedTextColor = color.tertiary
                )
            )
        }
    }
}