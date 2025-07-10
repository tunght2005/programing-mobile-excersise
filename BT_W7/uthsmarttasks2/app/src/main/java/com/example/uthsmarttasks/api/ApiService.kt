package com.example.uthsmarttasks.api

import com.example.uthsmarttasks.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("v2/product")
    suspend fun getProduct(): Product

}
