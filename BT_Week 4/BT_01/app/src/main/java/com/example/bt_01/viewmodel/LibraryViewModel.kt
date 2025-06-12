package com.example.bt_01.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bt_01.data.Book
import com.example.bt_01.data.Student

class LibraryViewModel {
    var currentStudentName by mutableStateOf("")
    val selectedBooks = mutableStateListOf<Book>()
    val students = mutableStateListOf<Student>()
    val allBooks = listOf(Book("Sách 01"), Book("Sách 02")) // Danh sách sách mẫu

    fun toggleBook(book: Book) {
        if (selectedBooks.contains(book)) {
            selectedBooks.remove(book)
        } else {
            selectedBooks.add(book)
        }
    }

    fun addOrUpdateStudent(name: String = currentStudentName) {
        val student = students.find { it.name == name }
        if (student == null) {
            students.add(Student(name, selectedBooks.toList()))
        } else {
            student.borrowedBooks = selectedBooks.toList()
        }
        selectedBooks.clear() // Xóa sách đã chọn sau khi thêm
    }
}