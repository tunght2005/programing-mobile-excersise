package com.example.profileui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.profileui.ui.ProfileScreen
import com.example.profileui.ui.theme.ProfileUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileUITheme {
                ProfileScreen()  // chỉ dùng UI này
            }
        }
    }
}
