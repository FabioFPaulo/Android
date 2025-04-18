package com.fabiofpaulo.navigationbasics.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiofpaulo.navigationbasics.components.BottomBar

@Composable
fun BottomNavbarTemplate(
    navController: NavHostController = rememberNavController(),
    content: @Composable ((navController: NavHostController) -> Unit)
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier.padding(innerPaddings)
        ) {
            content(navController)
        }
    }
}