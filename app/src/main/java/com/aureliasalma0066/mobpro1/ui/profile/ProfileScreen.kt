package com.aureliasalma0066.mobpro1.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aureliasalma0066.mobpro1.viewmodel.LoginViewModel

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val loginViewModel: LoginViewModel =
        viewModel()

    val name by
    loginViewModel.name.collectAsState()

    val email by
    loginViewModel.email.collectAsState()

    val photo by
    loginViewModel.photo.collectAsState()

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    val imagePicker =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            uri?.let {

                loginViewModel.savePhoto(
                    it.toString()
                )
            }
        }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(
                text = "Profile",
                style =
                    MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            if (photo.isNotEmpty()) {

                AsyncImage(
                    model = photo,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale =
                        ContentScale.Crop
                )

            } else {

                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.primaryContainer
                            ),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Text(
                            text = "👤"
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    imagePicker.launch(
                        "image/*"
                    )
                }
            ) {
                Text("Pilih Foto Profil")
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = name,
                style =
                    MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = email
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {
                    showLogoutDialog = true
                }
            ) {
                Text("Logout")
            }

            if (showLogoutDialog) {

                AlertDialog(
                    onDismissRequest = {
                        showLogoutDialog = false
                    },

                    title = {
                        Text("Konfirmasi Logout")
                    },

                    text = {
                        Text(
                            "Apakah Anda yakin ingin logout?"
                        )
                    },

                    confirmButton = {

                        TextButton(
                            onClick = {

                                loginViewModel.logout()

                                navController.navigate(
                                    "login"
                                ) {
                                    popUpTo(0)
                                }

                                showLogoutDialog = false
                            }
                        ) {
                            Text("Ya")
                        }
                    },

                    dismissButton = {

                        TextButton(
                            onClick = {
                                showLogoutDialog = false
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