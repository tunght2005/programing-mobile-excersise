package com.example.uibasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "start_screen") { // Bắt đầu từ start_screen
                composable("start_screen") {
                    StartCompose(navController)
                }
                composable("list_screen") {
                    ListScreen(navController)
                }
                composable("detail_screen/{componentName}") { backStackEntry ->
                    val componentName = backStackEntry.arguments?.getString("componentName") ?: ""
                    DetailScreen(componentName, navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartCompose(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 20.dp)
                ){
                    Text(
                        text = buildAnnotatedString {
                            append("Lê Văn Tùng\n")
                            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                                append("045205000787")
                            }
                        },
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black
            )
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Image",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Jetpack Compose",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }

        // Nút ở đáy
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate("list_screen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier
                    .width(315.dp)
                    .height(50.dp)
            ) {
                Text("I'm ready", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("UI Components List", fontSize = 24.sp, color = Color(0xFF2196F3), fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            uiComponentsList.forEach { (groupTitle, components) ->
                // groupTitle là "Display", "Input", "Layout", ...
                item {
                    Text(
                        text = groupTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp),
                        color = Color.Black
                    )
                }
                items(components) { component ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate("detail_screen/${component.name}")
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (component.name == "Tự tìm hiểu") Color(0xFFFFCDD2) else Color(0xFFBBDEFB)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = component.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = component.description,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(componentName: String, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "$componentName ",
                            fontSize = 20.sp,
                            color = Color(0xFF2196F3),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Text("<", fontSize = 24.sp, color = Color(0xFF2196F3))
                        // Hoặc dùng icon:
                        // Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF2196F3))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (componentName) {
                "Text" -> {
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle()) { append("The ") }

                            withStyle(
                                style = SpanStyle(textDecoration = TextDecoration.LineThrough)
                            ) { append("quick ") }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF8B4513) // Brown
                                )
                            ) { append("Brown ") }

                            append("fox ")

                            // spacing chữ "j u m p s"
                            "jumps".forEach { ch ->
                                append("$ch ")
                            }

                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) { append("over ") }

                            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("the") }

                            withStyle(
                                style = SpanStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 30.sp
                                )
                            ) { append(" lazy ") }

                            append("dog.")
                        },
                        fontSize = 40.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                "Image" -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image1),
                            contentDescription = "Ảnh trường UTH",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(380.dp)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(16.dp))

                        )
                        Text(
                            "https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
                            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.uth_bg_05),
                            contentDescription = "Sân trường",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(380.dp)
                                .height(200.dp)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Text("In app")
                    }
                }
                "TextField" -> {
                    var text by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        label = { Text("Thông tin nhập") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Text(
                        text = "Tự động cập nhật nội dung nhập vào textfield",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                "PasswordField" -> {
                    var password by remember { mutableStateOf("") }

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Mật khẩu") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(color = Color.White)
                    )

                    Text(
                        text = "Tự động cập nhật nội dung nhập vào textfield",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                "Column" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        if (it == 1) Color(0xFF66BB6A) else Color(0xFFA5D6A7) // màu nổi bật ở giữa
                                    )
                            )
                        }
                    }
                }
                "Row" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        repeat(4) { // 4 hàng
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                for (i in 0..2) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1.5f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                when (i) {
                                                    1 -> Color(0xFF42A5F5) // Cột giữa: xanh đậm hơn
                                                    else -> Color(0xFFBBDEFB) // Các cột còn lại: xanh nhạt
                                                }
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
                else -> {
                    Text(
                        text = "Detail for $componentName coming soon!",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}