package com.example.uibasic

data class UIComponent(
    val name: String,
    val description: String
)

val uiComponentsList = listOf(
    "Display" to listOf(
        UIComponent("Text", "Displays text"),
        UIComponent("Image", "Displays an image")
    ),
    "Input" to listOf(
        UIComponent("TextField", "Input field for text"),
        UIComponent("PasswordField", "Input field for passwords")
    ),
    "Layout" to listOf(
        UIComponent("Column", "Arranges elements vertically"),
        UIComponent("Row", "Arranges elements horizontally"),
        UIComponent("Tự tìm hiểu", "Tìm ra tất cả các thành phần UI Cơ bản")
    )
)
