package com.example.kuralify.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryBlue = Color(0xFF2196F3)
val LightBlue = Color(0xFFE3F2FD)
val DarkBlue = Color(0xFF0D47A1)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

private val ColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = LightBlue,
    tertiary = DarkBlue,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = DarkBlue,
    onTertiary = White,
    onBackground = Black,
    onSurface = Black,
)

@Composable
fun KuralifyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
} 