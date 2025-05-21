package com.example.thuchanh2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var email by remember { mutableStateOf("") }
            var result by remember { mutableStateOf("") }
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") // Add 1 cái biểu thức chính quy để ktra 1 email

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Thực Hành 02",
                    modifier = Modifier.padding(bottom = 20.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
//                    label = { Text("Nhập email") }, Hiển thị nằm owr viền khi forcus
                    placeholder = { Text("Nhập email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )
                Text(
                    text = result,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = Color.Red
                )
                Button(
                    onClick = {
                        result = when {
                            email.isEmpty() -> "Yêu cầu nhập Email"
                            !email.matches(emailRegex) -> "Email không đúng định dạng"
                            else -> "Bạn đã nhập email hợp lệ"
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .widthIn(min = 300.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text("Kiểm tra")
                }
            }
        }
    }
}