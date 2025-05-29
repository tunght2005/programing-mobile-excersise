package com.example.startcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartCompose()
        }
    }
}



@Composable
fun StartCompose() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Image",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text("Jetpack Compose", fontSize = 18.sp,  fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))
        Text(
            "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            )
        Spacer(modifier = Modifier.height(200.dp))
        Button(onClick = { /* action */ }) {
            Text("I'm ready")
        }
    }
}