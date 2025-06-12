package com.example.bt_02.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.bt_02.viewmodel.AuthViewModel
import com.example.bt_02.screen.ConfirmScreen
import com.example.bt_02.screen.ForgetPasswordScreen
import com.example.bt_02.screen.ResetPasswordScreen
import com.example.bt_02.screen.VerifyCodeScreen

@Composable
fun AppNavHost(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController, startDestination = "forget") {
        composable("forget") {
            ForgetPasswordScreen(navController, authViewModel)
        }
        composable("verify") {
            VerifyCodeScreen(navController, authViewModel)
        }
        composable("reset") {
            ResetPasswordScreen(navController, authViewModel)
        }
        composable("confirm") {
            ConfirmScreen(navController, authViewModel)
        }
    }
}
