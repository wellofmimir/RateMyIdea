package com.example.ratemyidea.common

import androidx.compose.runtime.Composable
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.ratemyidea.ui.theme.LocalAppTypography
import androidx.compose.ui.graphics.Color
import com.example.ratemyidea.ui.theme.LocalColorScheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp

@Composable
fun NavigationBarSection(
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
            containerColor = color.background,
        ) {
            NavigationBarItem (
                selected = selectedIndex == 0,
                onClick = { onItemSelected(0) },
                icon = {
                    Icon(Icons.Default.Info, contentDescription = "Ideas")
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
                    Icon (Icons.Default.Add, contentDescription = "Add")
                },
                label = {
                    Text (
                        text = "Add",
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
        }
    }
}