package com.caleb.savvy.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.caleb.savvy.ui.theme.AppTypography

private val DarkColorScheme = darkColorScheme(
    primary      = ButtonColor,
    onPrimary    = LightText,
    background   = WelcomeBackground,
    onBackground = LightText
)

private val LightColorScheme = lightColorScheme(
    primary      = ButtonColor,
    onPrimary    = DarkText,
    background   = WelcomeBackground,
    onBackground = DarkText

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
fun SavvyTheme(
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