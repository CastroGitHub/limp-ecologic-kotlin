
package com.lekotlin.frontend

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object LimpEcologicColors {
    val Verde = Color(0xFF63CF15)
    val Azul = Color(0xFF3375AD)
    val AzulClaro = Color(0xFF0F94F1)

    val Branco = Color(0xFFFFFFFF)
    val Texto = Color(0xFF222222)
    val Cinza = Color(0xFFF2F4F6)
}

private val LimpEcologicColorScheme = lightColorScheme(
    primary = LimpEcologicColors.Azul,
    onPrimary = LimpEcologicColors.Branco,

    secondary = LimpEcologicColors.Verde,
    onSecondary = LimpEcologicColors.Texto,

    tertiary = LimpEcologicColors.AzulClaro,
    onTertiary = LimpEcologicColors.Branco,

    background = LimpEcologicColors.Branco,
    onBackground = LimpEcologicColors.Texto,

    surface = LimpEcologicColors.Branco,
    onSurface = LimpEcologicColors.Texto
)

@Composable
fun LimpEcologicTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LimpEcologicColorScheme,
        content = content
    )
}
