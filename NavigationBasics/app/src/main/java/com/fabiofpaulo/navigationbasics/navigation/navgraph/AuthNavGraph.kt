package com.fabiofpaulo.navigationbasics.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fabiofpaulo.navigationbasics.navigation.AUTHENTICATION_ROUTE
import com.fabiofpaulo.navigationbasics.navigation.Screen
import com.fabiofpaulo.navigationbasics.ui.screens.LoginScreen
import com.fabiofpaulo.navigationbasics.ui.screens.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        startDestination = Screen.Login.route,
        route = AUTHENTICATION_ROUTE
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(navController)
        }
    }
}