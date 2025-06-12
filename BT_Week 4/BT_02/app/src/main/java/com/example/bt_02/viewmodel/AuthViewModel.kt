package com.example.bt_02.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email = mutableStateOf("")
    var code = mutableStateOf("")
    var password = mutableStateOf("")
}
