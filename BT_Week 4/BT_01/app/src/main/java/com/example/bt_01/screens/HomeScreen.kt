package com.example.bt_01.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bt_01.data.Book
import com.example.bt_01.viewmodel.LibraryViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: LibraryViewModel) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = bottomNavController,
                currentRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = bottomNavController, startDestination = "home") {
                composable("home") { ManageScreen(viewModel) }
                composable("add") { AddScreen(viewModel) }
                composable("list") { BookListScreen(viewModel) }
                composable("me") { StudentListScreen(viewModel) }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, currentRoute: String) {
    NavigationBar(
        modifier = Modifier,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Quản Lý") }
        )
        NavigationBarItem(
            selected = currentRoute == "list",
            onClick = { navController.navigate("list") },
            icon = { Icon(Icons.Filled.DateRange, contentDescription = "Danh sách") },
            label = { Text("Danh sách") }
        )
        NavigationBarItem(
            selected = currentRoute == "me",
            onClick = { navController.navigate("me") },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Tôi") },
            label = { Text("Sinh Viên") }
        )
    }
}

@Composable
fun ManageScreen(viewModel: LibraryViewModel) {
    var studentName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hệ thống Quản lý Thư viện",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentSize()
            )
        }
        Spacer(Modifier.height(30.dp))
        Text("Sinh Viên", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Sinh viên") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Text("Danh sách sách", style = MaterialTheme.typography.titleMedium)
        val books = viewModel.allBooks
        books.forEach { book ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewModel.selectedBooks.contains(book),
                    onCheckedChange = { viewModel.toggleBook(book) }
                )
                Text(book.title)
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (studentName.isNotBlank()) {
                viewModel.addOrUpdateStudent(studentName)
                studentName = ""
            }
        }) {
            Text("Thêm")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.students) { student ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "${student.name}", style = MaterialTheme.typography.bodyLarge)
                    if (student.borrowedBooks.isEmpty()) {
                        Text("Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!")
                    } else {
                        Text("Đã mượn: ${student.borrowedBooks.joinToString { it.title }}")
                    }
                }
            }
        }
    }
}

@Composable
fun AddScreen(viewModel: LibraryViewModel) {
    // Màn hình "Thêm" (có thể mở rộng tính năng sau)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Màn hình Thêm", style = MaterialTheme.typography.headlineSmall)
        // Thêm logic cho màn hình "Thêm" nếu cần
    }
}

@Composable
fun BookListScreen(viewModel: LibraryViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Danh sách Sách", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 20.dp))
        LazyColumn {
            items(viewModel.allBooks) { book ->
                Text(text = "${book.title}", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun StudentListScreen(viewModel: LibraryViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Danh sách Sinh viên", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 20.dp))
        LazyColumn {
            items(viewModel.students) { student ->
                Text(text = "${student.name}", modifier = Modifier.padding(8.dp))
            }
        }
    }
}