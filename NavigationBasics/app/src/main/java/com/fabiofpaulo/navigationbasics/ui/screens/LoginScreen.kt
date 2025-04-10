package com.fabiofpaulo.navigationbasics.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.fabiofpaulo.navigationbasics.navigation.PRIVATE_ROUTE
import com.fabiofpaulo.navigationbasics.navigation.Screen

@Composable
fun LoginScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                "Login",
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold,
            )
            TextButton(onClick = {
                navController.navigate(Screen.SignUp.route)
            }) {
                Text("Go to Register")
            }
            TextButton(onClick = {
                navController.navigate(PRIVATE_ROUTE)
            }) {
                Text("Go to Home")
            }
        }
    }
}