package com.fabiofpaulo.mmogames.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fabiofpaulo.mmogames.ui.screens.GamesScreen
import com.fabiofpaulo.mmogames.ui.screens.GiveawaysScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = BottomBarScreen.Games.route
    ){
        composable(
            route = BottomBarScreen.Games.route
        ) {
            GamesScreen()
        }

        composable(
            route = BottomBarScreen.Giveaways.route
        ) {
            GiveawaysScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
}