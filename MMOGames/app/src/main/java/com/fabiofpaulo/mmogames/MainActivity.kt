package com.fabiofpaulo.mmogames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiofpaulo.mmogames.navigation.NavGraph
import com.fabiofpaulo.mmogames.templates.BottomBarTemplate
import com.fabiofpaulo.mmogames.ui.screens.GamesScreen
import com.fabiofpaulo.mmogames.ui.theme.MMOGamesTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MMOGamesTheme {
                navController = rememberNavController()
                BottomBarTemplate(navController) {
                    NavGraph(navController)
                }
            }
        }
    }
}

