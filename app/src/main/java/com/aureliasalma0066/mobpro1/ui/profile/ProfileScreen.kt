package com.aureliasalma0066.mobpro1.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    Scaffold {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
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

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = "👤",
                    style =
                        MaterialTheme.typography.headlineLarge
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = name
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

                    loginViewModel.logout()

                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }
            ) {
                Text("Logout")
            }
        }
    }
}