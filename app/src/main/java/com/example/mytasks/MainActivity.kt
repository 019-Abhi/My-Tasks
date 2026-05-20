package com.example.mytasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.mytasks.ui.MainViewModel
import com.example.mytasks.ui.MainViewModelFactory
import com.example.mytasks.ui.navigation.AppNavHost
import com.example.mytasks.ui.theme.MyTasksTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable full edge-to-edge display
        enableEdgeToEdge()

        setContent {
            MyTasksTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}
