package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.ui.screens.ProductDetailScreen
import com.example.uthsmarttasks.ui.screens.ProductListScreen
import com.example.uthsmarttasks.ui.theme.UthsmarttasksTheme
import com.example.uthsmarttasks.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UthsmarttasksTheme {
                val navController = rememberNavController()
                val productViewModel: ProductViewModel = viewModel()

                NavHost(navController, startDestination = "product_list") {
                    composable("product_list") {
                        ProductListScreen(navController, productViewModel)
                    }
                    composable("product_detail") {
                        ProductDetailScreen(navController, productViewModel)
                    }
                }

            }
        }
    }
}
