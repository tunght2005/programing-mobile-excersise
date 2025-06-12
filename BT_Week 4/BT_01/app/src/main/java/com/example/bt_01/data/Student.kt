package com.example.bt_01.data


data class Student(val name: String, var borrowedBooks: List<Book> = emptyList())