package com.fabiofpaulo.navigationbasics.navigation.navgraph

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fabiofpaulo.navigationbasics.navigation.DETAIL_ARGUMENT_KEY
import com.fabiofpaulo.navigationbasics.navigation.DETAIL_ARGUMENT_KEY2
import com.fabiofpaulo.navigationbasics.navigation.PRIVATE_ROUTE
import com.fabiofpaulo.navigationbasics.navigation.Screen
import com.fabiofpaulo.navigationbasics.ui.screens.DetailScreen
import com.fabiofpaulo.navigationbasics.ui.screens.HomeScreen

fun NavGraphBuilder.privateNavGraph(navController: NavHostController){
    navigation(
        route = PRIVATE_ROUTE,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                },
                navArgument(DETAIL_ARGUMENT_KEY2) {
                    type = NavType.StringType
                }
            )
        ) {
            Log.d("Args Id", it.arguments?.getInt(DETAIL_ARGUMENT_KEY).toString())
            Log.d("Args Name", it.arguments?.getString(DETAIL_ARGUMENT_KEY2).toString())

            DetailScreen(navController)
        }
    }
}