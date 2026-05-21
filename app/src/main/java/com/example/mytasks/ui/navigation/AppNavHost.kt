package com.example.mytasks.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mytasks.MyTasksApplication
import com.example.mytasks.ui.MainViewModel
import com.example.mytasks.ui.screens.*
import com.example.mytasks.ui.viewmodel.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val app = context.applicationContext as MyTasksApplication

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }

        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(app.categoryRepository)
            )
            HomeScreen(
                viewModel = homeViewModel,
                onCategoryClick = { category ->
                    navController.navigate(Screen.ShortTermGoals.createRoute(category.id))
                },
                onViewAllClick = {
                    navController.navigate(Screen.CategoryManagement.createRoute("ST"))
                },
                onLongTermGoalsClick = {
                    navController.navigate(Screen.CategoryManagement.createRoute("LT"))
                }
            )
        }

        composable(
            route = Screen.ShortTermGoals.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getLong("categoryId") ?: -1L
            val shortTermViewModel: ShortTermViewModel = viewModel(
                factory = ShortTermViewModelFactory(app.taskRepository)
            )
            
            var categoryName by remember { mutableStateOf("Tasks") }
            LaunchedEffect(categoryId) {
                val category = app.categoryRepository.getCategoryById(categoryId)
                categoryName = category?.name ?: "Tasks"
            }
            
            ShortTermGoalsScreen(
                categoryId = categoryId,
                categoryName = categoryName,
                viewModel = shortTermViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.LongTermGoals.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getLong("categoryId") ?: -1L
            val longTermViewModel: LongTermViewModel = viewModel(
                factory = LongTermViewModelFactory(app.longTermGoalRepository, categoryId)
            )
            
            var categoryName by remember { mutableStateOf("Goal") }
            LaunchedEffect(categoryId) {
                val category = app.categoryRepository.getCategoryById(categoryId)
                categoryName = category?.name ?: "Goal"
            }
            
            LongTermGoalsScreen(
                categoryName = categoryName,
                viewModel = longTermViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.CategoryManagement.route,
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "ST"
            val categoryViewModel: CategoryManagementViewModel = viewModel(
                factory = CategoryManagementViewModelFactory(app.categoryRepository)
            )

            CategoryManagementScreen(
                type = type,
                viewModel = categoryViewModel,
                onCategoryClick = { category ->
                    if (type == "ST") {
                        navController.navigate(Screen.ShortTermGoals.createRoute(category.id))
                    } else {
                        navController.navigate(Screen.LongTermGoals.createRoute(category.id))
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
