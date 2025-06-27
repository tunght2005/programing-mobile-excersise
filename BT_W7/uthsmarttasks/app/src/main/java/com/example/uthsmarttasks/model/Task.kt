package com.example.uthsmarttasks.model

import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val color: Color
)
