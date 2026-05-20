package com.example.mytasks.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object ShortTermGoals : Screen("short_term_goals/{categoryId}") {
        fun createRoute(categoryId: Long) = "short_term_goals/$categoryId"
    }
    object LongTermGoals : Screen("long_term_goals/{categoryId}") {
        fun createRoute(categoryId: Long) = "long_term_goals/$categoryId"
    }
    object CategoryManagement : Screen("category_management/{type}") {
        fun createRoute(type: String) = "category_management/$type"
    }

    companion object {
        fun fromRoute(route: String?): Screen {
            if (route == null) return Home
            return when {
                route == Splash.route -> Splash
                route == Home.route -> Home
                route.startsWith("short_term_goals/") -> {
                    val idPart = route.substringAfter("short_term_goals/")
                    if (idPart.toLongOrNull() != null) ShortTermGoals else Home
                }
                route.startsWith("long_term_goals/") -> {
                    val idPart = route.substringAfter("long_term_goals/")
                    if (idPart.toLongOrNull() != null) LongTermGoals else Home
                }
                route.startsWith("category_management/") -> CategoryManagement
                else -> Home
            }
        }
    }
}
