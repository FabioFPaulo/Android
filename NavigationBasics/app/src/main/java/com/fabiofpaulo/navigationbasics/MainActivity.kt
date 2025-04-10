package com.fabiofpaulo.navigationbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiofpaulo.navigationbasics.navigation.navgraph.SetupNavGraph
import com.fabiofpaulo.navigationbasics.ui.theme.NavigationBasicsTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationBasicsTheme {
                navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}

