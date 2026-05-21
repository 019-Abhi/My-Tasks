package com.example.mytasks.ui.theme

import androidx.compose.ui.graphics.Color

// Light Theme - Modern Indigo & Slate
val PrimaryLight = Color(0xFF5D5FEF) // Royal Indigo
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFE0E0FF)
val OnPrimaryContainerLight = Color(0xFF110066)

val SecondaryLight = Color(0xFF535670)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFD9DAF6)
val OnSecondaryContainerLight = Color(0xFF10142B)

val TertiaryLight = Color(0xFF7E5260)
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFFFD8E4)
val OnTertiaryContainerLight = Color(0xFF31101D)

val BackgroundLight = Color(0xFFF8F9FF) // Soft Blue-ish tint for fresh look
val SurfaceLight = Color(0xFFFFFFFF)
val OnBackgroundLight = Color(0xFF1A1C2E)
val OnSurfaceLight = Color(0xFF1A1C2E)
val SurfaceVariantLight = Color(0xFFE2E2EC)
val OnSurfaceVariantLight = Color(0xFF45464F)

// Dark Theme - Deep Obsidian & Lavender
val PrimaryDark = Color(0xFFB8BBFF)
val OnPrimaryDark = Color(0xFF1A1D8E)
val PrimaryContainerDark = Color(0xFF3235A6)
val OnPrimaryContainerDark = Color(0xFFE0E0FF)

val SecondaryDark = Color(0xFFBCC2FF)
val OnSecondaryDark = Color(0xFF1B235E)
val SecondaryContainerDark = Color(0xFF333B76)
val OnSecondaryContainerDark = Color(0xFFD9DAF6)

val TertiaryDark = Color(0xFFEFB8C8)
val OnTertiaryDark = Color(0xFF492532)
val TertiaryContainerDark = Color(0xFF633B48)
val OnTertiaryContainerDark = Color(0xFFFFD8E4)

val BackgroundDark = Color(0xFF0F101A) // Deep Night
val SurfaceDark = Color(0xFF181926)    // Slightly lighter card surface
val OnBackgroundDark = Color(0xFFE4E4EB)
val OnSurfaceDark = Color(0xFFE4E4EB)
val SurfaceVariantDark = Color(0xFF45464F)
val OnSurfaceVariantDark = Color(0xFFC5C6D0)

// Star Color - Modern Marigold (Vibrant but sophisticated)
val StarredColor = Color(0xFFF7B500)

// Category Colors - Adjusted for "Excellent" high-end look
val CategoryGreen = Color(0xFF1DB954)  // Vibrant Mint/Green
val CategoryBlue = Color(0xFF00A3FF)   // Clear Sky Blue
val CategoryRed = Color(0xFFFF4D4D)    // Soft Coral Red
val CategoryPurple = Color(0xFFBD00FF) // Neon Purple
val CategoryPink = Color(0xFFFF007A)   // Hot Pink
val CategoryOrange = Color(0xFFFF9900)  // Vivid Orange
val CategoryCyan = Color(0xFF00D1FF)   // Electric Cyan
val CategoryIndigo = Color(0xFF5D5FEF) // Matching Primary Indigo

fun getCategoryColor(name: String): Color {
    return when (name.lowercase()) {
        "groceries" -> CategoryGreen
        "study" -> CategoryBlue
        "work" -> CategoryRed
        "personal" -> CategoryPink
        "coding" -> CategoryCyan
        "fitness" -> CategoryOrange
        "music" -> CategoryPurple
        else -> CategoryIndigo
    }
}
