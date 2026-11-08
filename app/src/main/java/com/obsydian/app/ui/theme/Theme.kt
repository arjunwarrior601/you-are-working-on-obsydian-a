package com.obsydian.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ElectricBlue,
    onPrimary = GraphiteDarkest,
    primaryContainer = GraphiteElevated,
    onPrimaryContainer = TitaniumWhite,
    secondary = TitaniumLight,
    onSecondary = GraphiteDarkest,
    background = GraphiteBackground,
    onBackground = TitaniumWhite,
    surface = GraphiteSurface,
    onSurface = TitaniumLight,
    surfaceVariant = GraphiteCard,
    onSurfaceVariant = TitaniumMedium,
    outline = GraphiteBorder
)

@Composable
fun ObsydianTheme(
    content: @Composable () -> Unit
) {
    val obsydianColors = ObsydianColors()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            window.statusBarColor = GraphiteDarkest.toArgb()
            window.navigationBarColor = GraphiteDarkest.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false
                isAppearanceLightNavigationBars = false
            }
        }
    }

    CompositionLocalProvider(
        LocalObsydianColors provides obsydianColors
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = ObsydianTypography,
            shapes = ObsydianShapes,
            content = content
        )
    }
}

object ObsydianTheme {
    val colors: ObsydianColors
        @Composable
        @ReadOnlyComposable
        get() = LocalObsydianColors.current
}
