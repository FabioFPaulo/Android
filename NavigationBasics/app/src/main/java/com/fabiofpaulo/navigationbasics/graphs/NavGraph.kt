package com.fabiofpaulo.navigationbasics.graphs

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fabiofpaulo.navigationbasics.Screen
import com.fabiofpaulo.navigationbasics.ui.screens.DetailScreen
import com.fabiofpaulo.navigationbasics.ui.screens.HomeScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(navController)
        }
    }
}