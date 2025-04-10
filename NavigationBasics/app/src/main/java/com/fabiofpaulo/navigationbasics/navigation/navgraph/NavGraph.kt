package com.fabiofpaulo.navigationbasics.navigation.navgraph

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fabiofpaulo.navigationbasics.navigation.AUTHENTICATION_ROUTE
import com.fabiofpaulo.navigationbasics.navigation.ROOT_ROUTE

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AUTHENTICATION_ROUTE,
        route = ROOT_ROUTE
    ) {

        privateNavGraph(navController)
        authNavGraph(navController)
    }
}