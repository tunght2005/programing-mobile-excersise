package com.example.thuchanh1_w4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh1_w4.screens.BasicScreen
import com.example.thuchanh1_w4.screens.WelcomeScreen
import com.example.thuchanh1_w4.screens.StartScreen
import com.example.thuchanh1_w4.ui.theme.ThucHanh1_W4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThucHanh1_W4Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "welcome_screen") {
                        composable("welcome_screen") { WelcomeScreen(navController) }
                        composable("start_screen") { StartScreen(navController) }
                        composable("basic_screen") { BasicScreen(navController) }
                    }
                }
            }
        }
    }
}