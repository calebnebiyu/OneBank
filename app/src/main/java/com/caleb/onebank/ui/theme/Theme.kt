package com.caleb.onebank.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = ButtonColor,
    onPrimary = Color.Black,
    background = LightBackground,
    onBackground = DarkText
)

private val DarkColorScheme = darkColorScheme(
    primary = ButtonColor,
    onPrimary = Color.White,
    background = DarkBackground,
    onBackground = LightText

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun OneBankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content:    @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme
        else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography  = AppTypography,
        content     = content
    )
}