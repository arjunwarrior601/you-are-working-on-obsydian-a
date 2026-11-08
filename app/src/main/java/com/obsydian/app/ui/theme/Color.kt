package com.obsydian.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// --- Black Graphite Palette ---
val GraphiteDarkest = Color(0xFF090B0E)
val GraphiteBackground = Color(0xFF0E1116)
val GraphiteSurface = Color(0xFF151922)
val GraphiteCard = Color(0xFF1C222E)
val GraphiteElevated = Color(0xFF232A38)
val GraphiteBorder = Color(0xFF2E3849)
val GraphiteBorderSubtle = Color(0xFF1E2530)

// --- Titanium Sheen Palette ---
val TitaniumWhite = Color(0xFFF9FAFB)
val TitaniumLight = Color(0xFFE5E7EB)
val TitaniumMedium = Color(0xFF9CA3AF)
val TitaniumMuted = Color(0xFF6B7280)
val TitaniumDark = Color(0xFF374151)
val TitaniumBorder = Color(0xFF4B5563)

// --- Electric Blue Neon Highlights ---
val ElectricBlue = Color(0xFF00D2FF)
val ElectricBlueDim = Color(0xFF00A2C7)
val ElectricBlueDark = Color(0xFF006B99)
val ElectricBlueGlow = Color(0x3300D2FF)
val ElectricBlueSubtle = Color(0x1400D2FF)

// --- Functional Status Colors ---
val StatusOffline = Color(0xFF10B981) // Emerald green for verified offline
val StatusWarning = Color(0xFFF59E0B)
val StatusError = Color(0xFFEF4444)

@Immutable
data class ObsydianColors(
    val background: Color = GraphiteBackground,
    val surface: Color = GraphiteSurface,
    val card: Color = GraphiteCard,
    val elevated: Color = GraphiteElevated,
    val border: Color = GraphiteBorder,
    val borderSubtle: Color = GraphiteBorderSubtle,
    val textPrimary: Color = TitaniumWhite,
    val textSecondary: Color = TitaniumMedium,
    val textMuted: Color = TitaniumMuted,
    val accent: Color = ElectricBlue,
    val accentDim: Color = ElectricBlueDim,
    val accentDark: Color = ElectricBlueDark,
    val accentGlow: Color = ElectricBlueGlow,
    val accentSubtle: Color = ElectricBlueSubtle,
    val titanium: Color = TitaniumLight,
    val titaniumBorder: Color = TitaniumBorder,
    val statusOffline: Color = StatusOffline
)

val LocalObsydianColors = staticCompositionLocalOf { ObsydianColors() }
