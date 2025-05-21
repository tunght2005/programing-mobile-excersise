package com.example.bt_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgeScreen()
        }
    }
}

@Composable
fun AgeScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "THỰC HÀNH 01",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically //Giữa theo chiều dọc
                ) {
                Text(
                    text = "Họ và tên",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 24.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f)) // Đẩy phần tử sau Spacer sang phải
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .width(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Tuổi",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 24.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    modifier = Modifier
                        .width(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val ageInt = age.toIntOrNull() ?: -1
                val ageGroup = when {
                    ageInt in 0..1 -> "Em bé"
                    ageInt in 2..5 -> "Trẻ em"
                    ageInt in 6..65 -> "Người lớn"
                    ageInt > 65 -> "Người già"
                    else -> "Tuổi không hợp lệ"
                }
                result = "Họ và tên: $name\nNhóm tuổi: $ageGroup"
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)), // Xanh dương đậm
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text("Kiểm tra", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = result,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue,
            style = TextStyle(fontSize = 24.sp),
            fontWeight = FontWeight.Bold,
        )
    }
}
