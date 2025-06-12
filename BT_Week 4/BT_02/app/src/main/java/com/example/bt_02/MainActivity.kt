package com.example.bt_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.bt_02.navigation.AppNavHost
import com.example.bt_02.viewmodel.AuthViewModel
import com.example.bt_02.ui.theme.BT_02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lấy ViewModel theo scope của Activity
        val viewModel: AuthViewModel by viewModels()

        setContent {
            BT_02Theme {
                val navController = rememberNavController()
                AppNavHost(navController = navController, authViewModel = viewModel)
            }
        }
    }
}
