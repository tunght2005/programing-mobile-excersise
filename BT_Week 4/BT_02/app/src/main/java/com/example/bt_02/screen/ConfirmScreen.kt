package com.example.bt_02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt_02.viewmodel.AuthViewModel
import androidx.navigation.NavController
import com.example.bt_02.R
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun ConfirmScreen(navController: NavController, viewModel: AuthViewModel) {
    var passwordVisibility by remember { mutableStateOf(false)}
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
            text = "Forget Password?",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter your Email, we will send you a verification code.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Email Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            },
            label = { Text("") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
//        Text("Email: ${viewModel.email.value}")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.code.value,
            onValueChange = { viewModel.code.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            },
            label = { Text("") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
//        Text("Code: ${viewModel.code.value}")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisibility) R.drawable.visibility else R.drawable.visibilityoff),
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
//        Text("Password: ${viewModel.password.value}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("forget")
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


