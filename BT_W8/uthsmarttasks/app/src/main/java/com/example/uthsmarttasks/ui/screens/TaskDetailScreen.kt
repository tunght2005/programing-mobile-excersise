package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    navController: NavController,
    viewModel: TaskViewModel
) {
    val task by viewModel.selectedTask.collectAsState()
    val scope = rememberCoroutineScope()

    // Gọi API khi màn hình hiển thị
    LaunchedEffect(taskId) {
        viewModel.fetchTaskById(taskId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            viewModel.deleteTask(taskId) {
                                navController.popBackStack() // truyền lambda onSuccess
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }

                }
            )
        }
    ) { innerPadding ->
        task?.let { t ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = t.title ?: "",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = t.description.orEmpty(),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Subtasks",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                (t.subtasks ?: emptyList()).forEach { subtask ->
                    Text(
                        text = subtask.title.orEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .padding(vertical = 6.dp),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Attachments",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                (t.attachments ?: emptyList()).forEach { file ->
                    Text(
                        text = file.fileName.orEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .padding(vertical = 6.dp),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
