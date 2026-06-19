package com.aureliasalma0066.mobpro1.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aureliasalma0066.mobpro1.ui.edit.EditScreen
import com.aureliasalma0066.mobpro1.ui.home.HomeScreen
import com.aureliasalma0066.mobpro1.ui.login.LoginScreen
import com.aureliasalma0066.mobpro1.ui.profile.ProfileScreen
import com.aureliasalma0066.mobpro1.ui.upload.UploadScreen
import com.aureliasalma0066.mobpro1.viewmodel.FilmViewModel
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

        composable(
            route = "edit/{id}"
        ) { backStackEntry ->

            val id =
                backStackEntry.arguments
                    ?.getString("id")
                    ?.toLongOrNull()

            val filmViewModel: FilmViewModel =
                viewModel()

            val films by
            filmViewModel.films.collectAsState()

            val film =
                films.find {
                    it.id == id
                }

            if (film != null) {

                EditScreen(
                    navController = navController,
                    film = film
                )

            } else {

                Box(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator()
                }
            }
        }
    }
}