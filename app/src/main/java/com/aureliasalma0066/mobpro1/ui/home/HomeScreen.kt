package com.aureliasalma0066.mobpro1.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aureliasalma0066.mobpro1.viewmodel.FilmViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.aureliasalma0066.mobpro1.viewmodel.LoginViewModel
import androidx.compose.ui.Alignment
import com.aureliasalma0066.mobpro1.viewmodel.ApiStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

    val filmViewModel: FilmViewModel = viewModel()

    val loginViewModel: LoginViewModel =
        viewModel()

    val email by loginViewModel.email.collectAsState()

    val films by filmViewModel.films.collectAsState()

    val status by filmViewModel.status.collectAsState()

    LaunchedEffect(email) {

        if (email.isNotBlank()) {

            filmViewModel.getFilms(
                email
            )
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

            when (status) {

                ApiStatus.LOADING -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                ApiStatus.FAILED -> {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment =
                            Alignment.CenterHorizontally,
                        verticalArrangement =
                            Arrangement.Center
                    ) {

                        Text(
                            text =
                                "Gagal memuat data.\nPeriksa koneksi internet."
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(
                            onClick = {

                                filmViewModel.getFilms(
                                    email
                                )
                            }
                        ) {
                            Text("Coba Lagi")
                        }
                    }
                }

                ApiStatus.SUCCESS -> {

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

                                    if (!film.image_url.isNullOrEmpty()) {

                                        AsyncImage(
                                            model = film.image_url,
                                            contentDescription = film.title,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(200.dp),
                                            contentScale =
                                                ContentScale.Crop
                                        )

                                        Spacer(
                                            modifier =
                                                Modifier.height(8.dp)
                                        )
                                    }

                                    Text(
                                        text = film.title
                                    )

                                    Text(
                                        text = film.review
                                    )

                                    Text(
                                        text =
                                            "Rating: ${film.rating}"
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(8.dp)
                                    )

                                    var showDialog by remember {
                                        mutableStateOf(false)
                                    }

                                    Button(
                                        onClick = {
                                            showDialog = true
                                        }
                                    ) {
                                        Text("Hapus")
                                    }

                                    if (showDialog) {

                                        AlertDialog(
                                            onDismissRequest = {
                                                showDialog = false
                                            },

                                            title = {
                                                Text("Konfirmasi")
                                            },

                                            text = {
                                                Text(
                                                    "Yakin ingin menghapus film ini?"
                                                )
                                            },

                                            confirmButton = {

                                                TextButton(
                                                    onClick = {

                                                        film.id?.let {

                                                            filmViewModel.deleteFilm(
                                                                it,
                                                                film.user_id
                                                            )
                                                        }

                                                        showDialog = false
                                                    }
                                                ) {
                                                    Text("Ya")
                                                }
                                            },

                                            dismissButton = {

                                                TextButton(
                                                    onClick = {
                                                        showDialog = false
                                                    }
                                                ) {
                                                    Text("Tidak")
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}