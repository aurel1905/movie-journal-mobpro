package com.aureliasalma0066.mobpro1.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Text(
                    text = "🎬 Movie Journal",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text("Nama")
                    },
                    modifier = Modifier.fillMaxWidth()
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
                        Text("Email")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        if (name.isBlank()) return@Button

                        if (email.isBlank()) return@Button

                        viewModel.login(
                            name,
                            email
                        )

                        navController.navigate("home") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text("Login")
                }
            }
        }
    }
}