package com.example.datastore.ui.themeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastore.viewmodel.ThemeViewModel
import com.example.datastore.ui.themeapp.AppTheme

@Composable
fun ThemeSelectionScreen(viewModel: ThemeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Setting", fontSize = 24.sp)

        Row(Modifier.padding(top = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ThemeButton(color = Color(0xFF03A9F4), onClick = { viewModel.setTheme(AppTheme.BLUE) })
            ThemeButton(color = Color(0xFFE91E63), onClick = { viewModel.setTheme(AppTheme.PINK) })
            ThemeButton(color = Color.Black, onClick = { viewModel.setTheme(AppTheme.DARK) })
        }

        Button(
            modifier = Modifier.padding(top = 32.dp),
            onClick = { /* maybe navigate to detail */ }
        ) {
            Text("Apply")
        }
    }
}

@Composable
fun ThemeButton(color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() }
    )
}
