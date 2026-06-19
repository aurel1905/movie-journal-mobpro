package com.aureliasalma0066.mobpro1.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aureliasalma0066.mobpro1.model.Film
import com.aureliasalma0066.mobpro1.viewmodel.FilmViewModel
import com.aureliasalma0066.mobpro1.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navController: NavController,
    film: Film
) {

    val filmViewModel: FilmViewModel =
        viewModel()

    val loginViewModel: LoginViewModel =
        viewModel()

    val email by
    loginViewModel.email.collectAsState()

    var title by remember {
        mutableStateOf(film.title)
    }

    var review by remember {
        mutableStateOf(film.review)
    }

    var rating by remember {
        mutableStateOf(
            film.rating.toString()
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Edit Film")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("←")
                    }
                }
            )
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                label = {
                    Text("Judul Film")
                },
                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = review,
                onValueChange = {
                    review = it
                },
                label = {
                    Text("Review")
                },
                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = rating,
                onValueChange = {
                    rating = it
                },
                label = {
                    Text("Rating")
                },
                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            Button(
                modifier =
                    Modifier.fillMaxWidth(),

                onClick = {

                    film.id?.let {

                        filmViewModel.updateFilm(
                            id = it,

                            film = Film(
                                id = it,
                                user_id = email,
                                title = title,
                                review = review,
                                rating =
                                    rating.toIntOrNull()
                                        ?: 0,
                                image_url =
                                    film.image_url
                            )
                        )
                    }

                    navController.popBackStack()
                }
            ) {

                Text("Update Film")
            }
        }
    }
}