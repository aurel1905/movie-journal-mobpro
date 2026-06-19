package com.aureliasalma0066.mobpro1.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aureliasalma0066.mobpro1.ui.home.HomeScreen
import com.aureliasalma0066.mobpro1.ui.login.LoginScreen
import com.aureliasalma0066.mobpro1.ui.profile.ProfileScreen
import com.aureliasalma0066.mobpro1.ui.upload.UploadScreen
import com.aureliasalma0066.mobpro1.viewmodel.LoginViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val loginViewModel: LoginViewModel =
        viewModel()

    val isLoggedIn by
    loginViewModel.isLoggedIn.collectAsState()

    val startDestination =
        if (isLoggedIn)
            "home"
        else
            "login"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable("upload") {
            UploadScreen(navController)
        }
    }
}