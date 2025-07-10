package com.example.uthsmarttasks.api

import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.model.TaskResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("tasks")
    suspend fun getTasks(): TaskResponse

    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): Task

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}
