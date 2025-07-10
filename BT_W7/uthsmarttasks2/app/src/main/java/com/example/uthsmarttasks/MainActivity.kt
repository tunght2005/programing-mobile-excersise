// MainActivity.kt
package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.collectAsState
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
                        val selected = productViewModel.selectedProduct.collectAsState().value
                        if (selected != null) {
                            ProductDetailScreen(navController, productViewModel)
                        } else {
                            // Fallback nếu không có sản phẩm nào được chọn
                            androidx.compose.material3.Text(
                                text = "Chưa chọn sản phẩm",
                                modifier = androidx.compose.ui.Modifier
                                    .fillMaxSize()
                                    .wrapContentSize()
                            )
                        }
                    }
                }
            }
        }
    }
}