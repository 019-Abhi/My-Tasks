package com.example.mytasks.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color

// Light Theme - Professional Slate & Indigo
val PrimaryLight = Color(0xFF5D5FEF)
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFEBEBFF)
val OnPrimaryContainerLight = Color(0xFF110066)

val SecondaryLight = Color(0xFF535670)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFE2E3FF)
val OnSecondaryContainerLight = Color(0xFF10142B)

val TertiaryLight = Color(0xFF7E5260)
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFFFD8E4)
val OnTertiaryContainerLight = Color(0xFF31101D)

val BackgroundLight = Color(0xFFF9FAFF)
val SurfaceLight = Color(0xFFFFFFFF)
val OnBackgroundLight = Color(0xFF1A1C2E)
val OnSurfaceLight = Color(0xFF1A1C2E)
val SurfaceVariantLight = Color(0xFFE2E2EC)
val OnSurfaceVariantLight = Color(0xFF45464F)

// Dark Theme - Sophisticated Charcoal & Soft Indigo (Eye-friendly)
val PrimaryDark = Color(0xFF94A3B8) // Muted Slate Blue/Gray
val OnPrimaryDark = Color(0xFF0F172A)
val PrimaryContainerDark = Color(0xFF1E293B) // Deep Slate
val OnPrimaryContainerDark = Color(0xFFF1F5F9)

val SecondaryDark = Color(0xFF64748B) // Muted Slate
val OnSecondaryDark = Color(0xFF0F172A)
val SecondaryContainerDark = Color(0xFF334155)
val OnSecondaryContainerDark = Color(0xFFCBD5E1)

val TertiaryDark = Color(0xFF94A3B8)
val OnTertiaryDark = Color(0xFF1E293B)
val TertiaryContainerDark = Color(0xFF0F172A)
val OnTertiaryContainerDark = Color(0xFFE2E8F0)

val BackgroundDark = Color(0xFF0B0C10) // Deep Dark Charcoal
val SurfaceDark = Color(0xFF16181D)    // Soft Obsidian Surface
val OnBackgroundDark = Color(0xFFE2E8F0)
val OnSurfaceDark = Color(0xFFE2E8F0)
val SurfaceVariantDark = Color(0xFF2D333B)
val OnSurfaceVariantDark = Color(0xFF94A3B8)

// Star Color - Elegant Golden Amber (Muted for Dark mode visibility)
val StarredColor = Color(0xFFFBBF24)

// --- Category Colors (Light Mode) ---
// Richer, slightly deeper tones so they don't look washed out on light backgrounds
val CategoryGreenLight = Color(0xFF16A34A)
val CategoryBlueLight = Color(0xFF2563EB)
val CategoryRedLight = Color(0xFFDC2626)
val CategoryPurpleLight = Color(0xFF7C3AED)
val CategoryPinkLight = Color(0xFFDB2777)
val CategoryOrangeLight = Color(0xFFEA580C)
val CategoryCyanLight = Color(0xFF0891B2)
val CategoryIndigoLight = Color(0xFF4F46E5)

// --- Category Colors (Dark Mode) ---
// Muted, pastel-tinted, lower saturation shades that look elegant against charcoal
val CategoryGreenDark = Color(0xFF86EFAC)
val CategoryBlueDark = Color(0xFF93C5FD)
val CategoryRedDark = Color(0xFFFCA5A5)
val CategoryPurpleDark = Color(0xFFC4B5FD)
val CategoryPinkDark = Color(0xFFFBCFE8)
val CategoryOrangeDark = Color(0xFFFDBA74)
val CategoryDarkCyan = Color(0xFF67E8F9)
val CategoryIndigoDark = Color(0xFFA5B4FC)

/**
 * Returns a tailored color asset for the category.
 * Made as a @Composable function to automatically adapt to dark/light themes.
 */
@Composable
fun getCategoryColor(name: String): Color {
    val isDark = isSystemInDarkTheme()
    return when (name.lowercase()) {
        "groceries" -> if (isDark) CategoryGreenDark else CategoryGreenLight
        "study"     -> if (isDark) CategoryBlueDark else CategoryBlueLight
        "work"      -> if (isDark) CategoryRedDark else CategoryRedLight
        "personal"  -> if (isDark) CategoryPinkDark else CategoryPinkLight
        "coding"    -> if (isDark) CategoryDarkCyan else CategoryCyanLight
        "fitness"   -> if (isDark) CategoryOrangeDark else CategoryOrangeLight
        "music"     -> if (isDark) CategoryPurpleDark else CategoryPurpleLight
        else        -> if (isDark) CategoryIndigoDark else CategoryIndigoLight
    }
}