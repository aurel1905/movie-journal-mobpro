package com.aureliasalma0066.mobpro1.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aureliasalma0066.mobpro1.viewmodel.FilmViewModel
import com.aureliasalma0066.mobpro1.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

    val filmViewModel: FilmViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()

    val films by filmViewModel.films.collectAsState()
    val loading by filmViewModel.loading.collectAsState()

    val email by loginViewModel.email.collectAsState()

    LaunchedEffect(email) {

        if (email.isNotBlank()) {
            filmViewModel.getFilms(email)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movie Journal")
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

            Row {

                Button(
                    onClick = {
                        navController.navigate("profile")
                    }
                ) {
                    Text("Profile")
                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Button(
                    onClick = {
                        navController.navigate("upload")
                    }
                ) {
                    Text("Tambah")
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (loading) {

                CircularProgressIndicator()

            } else {

                LazyColumn {

                    items(films) { film ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {

                                Text(film.title)

                                Text(film.review)

                                Text(
                                    "Rating: ${film.rating}"
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Button(
                                    onClick = {

                                        film.id?.let {

                                            filmViewModel.deleteFilm(
                                                it,
                                                film.user_id
                                            )
                                        }
                                    }
                                ) {
                                    Text("Hapus")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}