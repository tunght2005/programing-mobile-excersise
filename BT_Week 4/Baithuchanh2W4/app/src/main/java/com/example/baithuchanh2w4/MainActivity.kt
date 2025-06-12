package com.example.baithuchanh2w4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHNavigationApp()
        }
    }
}

@Composable
fun UTHNavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("onboarding/{step}/{title}/{description}") { backStackEntry ->
            val step = backStackEntry.arguments?.getString("step")?.toInt() ?: 1
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            OnboardingScreen(step, title, description, navController)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(
            "onboarding/1/Easy Time Management/With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first."
        ) {
            popUpTo("splash") { inclusive = true }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("UTH SmartTasks",
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF006EE9),
                fontSize = 37.sp
            )

        }
    }
}

@Composable
fun OnboardingScreen(
    step: Int,
    title: String,
    description: String,
    navController: NavController
) {
    val imageRes = when (step) {
        1 -> R.drawable.bro
        2 -> R.drawable.bro1
        3 -> R.drawable.bro2
        else -> R.drawable.bro
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //TopBar: Step indicator + Skip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                repeat(3) { index ->
                    val isSelected = (index + 1) == step
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 20.dp else 16.dp)
                            .padding(4.dp)
                            .background(
                                color = if (isSelected) Color(0xFF2196F3) else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }
            }

            TextButton(onClick = {
                navController.navigate("splash") {
                    popUpTo("splash") { inclusive = true }
                }
            }) {
                Text("Skip", color = Color(0xFF2196F3))
            }
        }

        Spacer(Modifier.height(70.dp))
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f)) // Cho nằm dưới

        //Bottom Navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (step > 1) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF2196F3), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(0.dp))
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    when (step) {
                        1 -> navController.navigate(
                            "onboarding/2/Increase Work Effectiveness/Time management and the determination of more important tasks will give your job statistics better and always improve."
                        )
                        2 -> navController.navigate(
                            "onboarding/3/Reminder Notification/The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set."
                        )
                        3 -> navController.navigate("splash") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .height(48.dp)
                    .width(290.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.White
                )
            ) {
                Text(text = if (step == 3) "Get Started" else "Next", fontWeight = FontWeight.Bold)
            }
        }
    }
}


