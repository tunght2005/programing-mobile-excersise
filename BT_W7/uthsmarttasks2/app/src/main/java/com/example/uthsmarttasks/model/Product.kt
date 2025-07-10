package com.example.uthsmarttasks.model

import androidx.annotation.DrawableRes

//data class Product(
//    val id: Int,
//    val name: String,
//    val price: String,
//    @DrawableRes val imageResId: Int,
//    val description: String
//)
// theo dử liệu api
data class Product(
    val id: String,
    val name: String,
    val des: String,
    val price: Double,
    val imgURL: String
)
