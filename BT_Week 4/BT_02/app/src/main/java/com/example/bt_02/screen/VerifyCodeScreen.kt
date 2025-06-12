package com.example.bt_02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bt_02.R
import com.example.bt_02.viewmodel.AuthViewModel

@Composable
fun VerifyCodeScreen(navController: NavController, viewModel: AuthViewModel) {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF2196F3), shape = CircleShape)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = Modifier.size(130.dp)
        )
        Text("SmartTasks", color = Color(0xFF2196F3), fontWeight = FontWeight.Bold, fontSize = 30.sp)
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Verify Code",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                append("Enter the code we sent to ")
                withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                    append(viewModel.email.value)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(5) { index ->
                val char = code.getOrNull(index)?.toString() ?: ""
                OutlinedTextField(
                    value = char,
                    onValueChange = {
                        if (it.length <= 1 && it.all { c -> c.isDigit() }) {
                            val newCode = buildString {
                                append(code.take(index))
                                append(it)
                                append(code.drop(index + 1))
                            }.take(5)
                            code = newCode
                        }
                    },
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    maxLines = 1
                )
            }
        }

        Button(
            onClick = {
                viewModel.code.value = code
                navController.navigate("reset")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(50.dp)
        ) {
            Text("Next", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
