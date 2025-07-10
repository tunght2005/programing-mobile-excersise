package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.uthsmarttasks.viewmodel.ProductViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, viewModel: ProductViewModel) {
    val product by viewModel.selectedProduct.collectAsState()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
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
                    text = "Product detail",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF03A9F4)
                    )
                )

                Spacer(modifier = Modifier.size(40.dp))
            }
        }
    ) { innerPadding ->
        product?.let { p ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 35.dp, vertical = 10.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(p.imgURL),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = p.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Giá: ${p.price}₫",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    color = Color(0xFFD60A0A)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    tonalElevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = p.des,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}


//
//@Composable
//fun ProductDetailScreen(navController: NavController, viewModel: ProductViewModel) {
//    val product by viewModel.product.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.fetchProduct()
//    }
//
//    Scaffold(
//        topBar = {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 35.dp, vertical = 40.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(
//                    onClick = { navController.popBackStack() },
//                    modifier = Modifier
//                        .size(40.dp)
//                        .background(Color(0xFF03A9F4), shape = RoundedCornerShape(16.dp))
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "Back",
//                        tint = Color.White
//                    )
//                }
//
//                Text(
//                    text = "Product detail",
//                    style = MaterialTheme.typography.titleLarge.copy(
//                        fontSize = 26.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF03A9F4)
//                    )
//                )
//
//                Spacer(modifier = Modifier.size(40.dp))
//            }
//        }
//    ) { innerPadding ->
//        product?.let {
//            Column(
//                modifier = Modifier
//                    .padding(innerPadding)
//                    .padding(horizontal = 35.dp, vertical = 10.dp)
//            ) {
//                Image(
//                    painter = rememberAsyncImagePainter(it.imgURL),
//                    contentDescription = "Product Image",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(350.dp)
//                        .clip(RoundedCornerShape(20.dp))
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Text(
//                    text = it.name,
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.SemiBold
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Giá: ${it.price}₫",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 27.sp,
//                    color = Color(0xFFD60A0A)
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Surface(
//                    shape = RoundedCornerShape(12.dp),
//                    tonalElevation = 4.dp,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(
//                        text = it.des,
//                        modifier = Modifier.padding(12.dp),
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
//        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    }
//}


