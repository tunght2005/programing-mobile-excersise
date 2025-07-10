package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.ui.screens.AddTaskScreen
import com.example.uthsmarttasks.ui.screens.TaskDetailScreen
import com.example.uthsmarttasks.ui.screens.TaskListScreen
import com.example.uthsmarttasks.ui.theme.UthsmarttasksTheme
import com.example.uthsmarttasks.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UthsmarttasksTheme {
                val navController = rememberNavController()
                val viewModel: TaskViewModel = viewModel()

                NavHost(navController, startDestination = "list") {
                    composable("detail/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                        if (taskId != null) {
                            TaskDetailScreen(
                                taskId = taskId,
                                navController = navController,     // truyền vào navController
                                viewModel = viewModel              // dùng chung viewModel
                            )
                        }
                    }

                    composable("list") {
                        TaskListScreen(navController, viewModel)
                    }
                    composable("add") {
                        AddTaskScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}
