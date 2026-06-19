package com.aureliasalma0066.mobpro1.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aureliasalma0066.mobpro1.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController
) {

    val viewModel: LoginViewModel = viewModel()

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),

                elevation = CardDefaults
                    .elevatedCardElevation(
                        defaultElevation = 8.dp
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "🎬",
                        style = MaterialTheme
                            .typography
                            .displayLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Movie Journal",
                        style = MaterialTheme
                            .typography
                            .headlineMedium
                    )

                    Text(
                        text =
                            "Catat film favoritmu dengan mudah",
                        style = MaterialTheme
                            .typography
                            .bodyMedium,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text("Nama")
                        },
                        modifier =
                            Modifier.fillMaxWidth(),

                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = {
                            Text("Email Gmail")
                        },
                        modifier =
                            Modifier.fillMaxWidth(),

                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    if (errorMessage.isNotEmpty()) {

                        Card(
                            colors =
                                CardDefaults.cardColors(
                                    containerColor =
                                        MaterialTheme
                                            .colorScheme
                                            .errorContainer
                                )
                        ) {

                            Text(
                                text = errorMessage,
                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .error,
                                modifier =
                                    Modifier.padding(
                                        12.dp
                                    )
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )
                    }

                    Button(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(52.dp),

                        onClick = {

                            when {

                                name.isBlank() -> {
                                    errorMessage =
                                        "Nama harus diisi"
                                }

                                email.isBlank() -> {
                                    errorMessage =
                                        "Email harus diisi"
                                }

                                !email.endsWith("@gmail.com") -> {
                                    errorMessage =
                                        "Gunakan email Gmail"
                                }

                                else -> {

                                    errorMessage = ""

                                    viewModel.login(
                                        name,
                                        email
                                    )

                                    navController.navigate(
                                        "home"
                                    ) {
                                        popUpTo("login") {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                    ) {

                        Text(
                            text = "Masuk"
                        )
                    }
                }
            }
        }
    }
}