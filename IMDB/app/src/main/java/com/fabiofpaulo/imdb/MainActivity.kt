package com.fabiofpaulo.imdb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fabiofpaulo.imdb.ui.screens.movieDetails.MovieDetails
import com.fabiofpaulo.imdb.ui.screens.moviesList.MoviesList
import com.fabiofpaulo.imdb.ui.theme.IMDBTheme
import com.fabiofpaulo.imdb.util.Event
import com.fabiofpaulo.imdb.util.EventBus
import com.fabiofpaulo.imdb.util.Paths
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current.lifecycle

            // titles
            var title by remember { mutableStateOf("") }
            var subtitle by remember { mutableStateOf("") }
            var backbutton by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = lifecycleOwner) {
                lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    EventBus.events.collect { event ->
                        when (event) {
                            is Event.Toast -> {
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is Event.ChangeTitles -> {
                                title = event.title
                                subtitle = event.subtitle
                                backbutton = event.backButton
                            }
                        }
                    }
                }
            }

            val navController = rememberNavController()
            IMDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(title)
                        },
                        navigationIcon = {
                            if (backbutton) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        }
                    )
                }) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 10.dp)
                        .fillMaxSize()) {
                        NavHost(navController, startDestination = Paths.MoviesList) {
                            composable<Paths.MoviesList> {
                                MoviesList(onGoDetails = {
                                    navController.navigate(Paths.MovieDetails(it))
                                })
                            }

                            composable<Paths.MovieDetails> {
                                val model : Paths.MovieDetails = it.toRoute()
                                MovieDetails(model.id)
                            }
                        }
                    }
                }
            }
        }
    }
}

