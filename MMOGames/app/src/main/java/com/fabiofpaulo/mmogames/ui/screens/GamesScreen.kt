package com.fabiofpaulo.mmogames.ui.screens

import android.provider.CalendarContract
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fabiofpaulo.mmogames.viewModels.MMOViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.fabiofpaulo.mmogames.models.GameResponse

@Composable
fun GamesScreen(modifier: Modifier = Modifier) {
    val mmoViewModel: MMOViewModel = viewModel()

    val gamesListState by mmoViewModel.gamesListState



    when {
        gamesListState.loading -> {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        gamesListState.error != null -> {
            Text("ERROR OCCURRED")
        }

        else -> {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
            ) {
                TextField(
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = {
                        Text("Search title...")
                    },
                    value = mmoViewModel.gamesSearch.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(100.dp)),
                    onValueChange = {
                        mmoViewModel.onGameSearch(it)
                    }
                )
                GamesList(mmoViewModel.gamesFilterState.value)
            }
        }
    }

}

@Composable
private fun GamesList(items: List<GameResponse>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        items(items) {
            ListItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .animateItem(),
                headlineContent = {
                    Text(it.title)
                },
                supportingContent = {
                    Text(it.short_description)
                },
                leadingContent = {
                    AsyncImage(
                        model = it.thumbnail,
                        contentDescription = null
                    )
                },
                shadowElevation = 2.dp
            )
        }
    }
}