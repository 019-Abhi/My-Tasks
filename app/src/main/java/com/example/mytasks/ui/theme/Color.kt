package com.example.mytasks.ui.theme

import androidx.compose.ui.graphics.Color

// Ultra-Vibrant & Energetic Material 3 Palette
// High energy colors for a modern, productivity-focused look

// Primary: Electric Violet
val PrimaryLight = Color(0xFF7C4DFF)
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFEDE7F6)
val OnPrimaryContainerLight = Color(0xFF311B92)

// Secondary: Teal
val SecondaryLight = Color(0xFF00BFA5)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFE0F2F1)
val OnSecondaryContainerLight = Color(0xFF004D40)

// Tertiary: Rose
val TertiaryLight = Color(0xFFF50057)
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFFCE4EC)
val OnTertiaryContainerLight = Color(0xFF880E4F)

val PrimaryDark = Color(0xFFB39DDB)
val OnPrimaryDark = Color(0xFF311B92)
val PrimaryContainerDark = Color(0xFF4527A0)
val OnPrimaryContainerDark = Color(0xFFEDE7F6)

val SecondaryDark = Color(0xFF80CBC4)
val OnSecondaryDark = Color(0xFF004D40)
val SecondaryContainerDark = Color(0xFF00695C)
val OnSecondaryContainerDark = Color(0xFFE0F2F1)

val TertiaryDark = Color(0xFFF48FB1)
val OnTertiaryDark = Color(0xFF880E4F)
val TertiaryContainerDark = Color(0xFFAD1457)
val OnTertiaryContainerDark = Color(0xFFFCE4EC)

// Vibrant Category Colors
val CategoryGroceries = Color(0xFF43A047) // Vibrant Green
val CategoryStudy = Color(0xFF1E88E5)     // Brilliant Blue
val CategoryWork = Color(0xFFF4511E)      // Deep Orange
val CategoryOthers = Color(0xFF8E24AA)    // Vibrant Purple
val CategoryPersonal = Color(0xFFD81B60)   // Hot Pink
val CategoryFitness = Color(0xFFFB8C00)    // Amber/Orange
val CategoryCoding = Color(0xFF00ACC1)     // Cyan
val CategoryMusic = Color(0xFFF06292)      // Light Pink

fun getCategoryColor(name: String): Color {
    return when (name.lowercase()) {
        "groceries" -> CategoryGroceries
        "study" -> CategoryStudy
        "work" -> CategoryWork
        "personal" -> CategoryPersonal
        "coding" -> CategoryCoding
        "fitness" -> CategoryFitness
        "music" -> CategoryMusic
        else -> CategoryOthers
    }
}
