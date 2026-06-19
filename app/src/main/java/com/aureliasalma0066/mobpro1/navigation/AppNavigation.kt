package com.aureliasalma0066.mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aureliasalma0066.mobpro1.ui.home.HomeScreen
import com.aureliasalma0066.mobpro1.ui.login.LoginScreen
import com.aureliasalma0066.mobpro1.ui.profile.ProfileScreen
import com.aureliasalma0066.mobpro1.ui.upload.UploadScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
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