package com.aureliasalma0066.mobpro1.ui.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
fun UploadScreen(
    navController: NavController
) {

    val filmViewModel: FilmViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()

    val email by loginViewModel.email.collectAsState()

    var title by remember {
        mutableStateOf("")
    }

    var review by remember {
        mutableStateOf("")
    }

    var rating by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Tambah Film")
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                label = {
                    Text("Judul Film")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = review,
                onValueChange = {
                    review = it
                },
                label = {
                    Text("Review")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = rating,
                onValueChange = {
                    rating = it
                },
                label = {
                    Text("Rating")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    if (title.isBlank()) return@Button
                    if (review.isBlank()) return@Button
                    if (rating.isBlank()) return@Button
                    if (email.isBlank()) return@Button

                    filmViewModel.addFilm(
                        Film(
                            user_id = email,
                            title = title,
                            review = review,
                            rating = rating.toIntOrNull() ?: 0,
                            image_url = null
                        )
                    )

                    navController.popBackStack()
                }
            ) {
                Text("Simpan Film")
            }
        }
    }
}