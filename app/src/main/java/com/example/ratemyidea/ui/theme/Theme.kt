package com.example.ratemyidea.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColorScheme = staticCompositionLocalOf {
    LightScheme
}

private val LightScheme = darkColorScheme (
    background = BackgroundGray,
    surface = ForegroundWhite,
    tertiary = Color.Black,
    primary = Color.White

)
