package com.example.thuchanh1_w4

data class UIComponent(
    val name: String,
    val description: String
)

val uiComponentsList = listOf(
        UIComponent("Column", "Arranges elements vertically"),
        UIComponent("Row", "Arranges elements horizontally"),
        UIComponent("Tự tìm hiểu", "Tìm ra tất cả các thành phần UI Cơ bản")
    )
