package com.example.uthsmarttasks.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.uthsmarttasks.model.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(listOf(
//        Task(1, "Complete Android Project", "Finish the UI, integrate API, and write documentation", Color(0xFFB3E5FC)),
//        Task(2, "Complete Android Project", "Finish the UI, integrate API, and write documentation", Color(0xFFFFCDD2)),
//        Task(3, "Complete Android Project", "Finish the UI, integrate API, and write documentation", Color(0xFFC8E6C9)),
//        Task(4, "Complete Android Project", "Finish the UI, integrate API, and write documentation", Color(0xFFFFCDD2)),
    ))

    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(title: String, description: String) {
        val newTask = Task(
            id = _tasks.value.size + 1,
            title = title,
            description = description,
            color = Color(0xFFB3E5FC)
        )
        _tasks.value = _tasks.value + newTask
    }
}
