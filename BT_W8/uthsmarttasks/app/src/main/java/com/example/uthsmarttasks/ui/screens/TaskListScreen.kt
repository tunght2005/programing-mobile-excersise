package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel) {
    val taskList by viewModel.tasks.collectAsState()

    // Gọi API khi mở màn
    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                onBack = { navController.popBackStack() },
                onAdd = { navController.navigate("add") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                containerColor = Color(0xFF2196F3),
                shape = CircleShape,
                modifier = Modifier.offset(y = 46.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .height(100.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFF2196F3))
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.DateRange, contentDescription = "Calendar", tint = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(48.dp))
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.DateRange, contentDescription = "Tasks", tint = Color.Gray)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.DarkGray)
                    }
                }
            }
        }
    ) { innerPadding ->
        if (taskList.isEmpty()) {
            Text("Không có task nào", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(taskList) { task ->
                    TaskCard(task = task, onClick = {
                        navController.navigate("detail/${task.id}")
                    })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2196F3), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = task.title ?: "Không có tiêu đề",
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (!task.description.isNullOrBlank()) task.description else "Không có mô tả",
            fontWeight = FontWeight.W400,
            fontSize = 18.sp,
            color = Color.LightGray
        )
    }
}

@Composable
fun CustomTopAppBar(onBack: () -> Unit, onAdd: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF03A9F4), shape = RoundedCornerShape(16.dp))
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = "List",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF2196F3)
        )

        IconButton(
            onClick = onAdd,
            modifier = Modifier
                .size(40.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFF5722), Color(0xFFF44336))
                    ),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}