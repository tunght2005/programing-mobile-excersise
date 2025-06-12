package com.example.bt_01.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object StudentList : Screen("students")
    object BookList : Screen("books")
}
