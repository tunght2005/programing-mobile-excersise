package com.example.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datastore.ui.theme.DataStoreTheme
import com.example.datastore.ui.themeapp.AppTheme
import com.example.datastore.ui.themeapp.ThemeSelectionScreen
import com.example.datastore.viewmodel.ThemeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreTheme {
                ThemeApp()
            }
        }
    }
}

@Composable
fun ThemeApp(viewModel: ThemeViewModel = viewModel()) {
    val theme by viewModel.theme.collectAsState()

    val backgroundColor = when (theme) {
        AppTheme.LIGHT -> Color.White
        AppTheme.DARK -> Color(0xFF121212)
        AppTheme.PINK -> Color(0xFFE91E63)
        AppTheme.BLUE -> Color(0xFF03A9F4)
        else -> Color.White // đảm bảo exhaustive
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        ThemeSelectionScreen(viewModel)
    }
}
