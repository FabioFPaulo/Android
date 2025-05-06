package com.fabiofpaulo.mmogames.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiofpaulo.mmogames.models.GameResponse
import com.fabiofpaulo.mmogames.models.GiveawayResponse
import com.fabiofpaulo.mmogames.services.MMOPlatforms
import com.fabiofpaulo.mmogames.services.mmoApi
import kotlinx.coroutines.launch

class MMOViewModel : ViewModel() {
    private val _selectedPlatform = mutableStateOf<MMOPlatforms>(MMOPlatforms.all)


    private val _gamesListState = mutableStateOf(GamesListState())
    val gamesListState: State<GamesListState> = _gamesListState

    private val _gamesFilterState = mutableStateOf<List<GameResponse>>(listOf())
    val gamesFilterState: State<List<GameResponse>> = _gamesFilterState

    private val _gamesSearch = mutableStateOf<String>("")
    val gamesSearch: State<String> = _gamesSearch

    private val _giveawaysListState = mutableStateOf(GiveawaysListState())
    val giveawaysListState: State<GiveawaysListState> = _giveawaysListState

    init {
        fetchGamesList()
        fetchGiveAwaysList()
    }

    fun onGameSearch(value: String) {
        _gamesSearch.value = value
        _gamesFilterState.value =
            _gamesListState.value.list.filter { it.title.contains(value, ignoreCase = true) }
    }

    private fun fetchGamesList() {
        viewModelScope.launch {
            try {
                val response = mmoApi.getMMOGamesByPlatform(_selectedPlatform.value.name)
                _gamesListState.value = _gamesListState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _gamesListState.value = _gamesListState.value.copy(
                    loading = false,
                    error = "Error fetching games ${e.message}"
                )
            }
        }
    }

    private fun fetchGiveAwaysList() {
        viewModelScope.launch {
            try {
                val response = mmoApi.getGiveaways()
                _giveawaysListState.value = _giveawaysListState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _giveawaysListState.value = _giveawaysListState.value.copy(
                    loading = false,
                    error = "Error fetching giveaways ${e.message}"
                )
            }
        }
    }

    data class GamesListState(
        val loading: Boolean = true,
        val list: List<GameResponse> = emptyList(),
        val error: String? = null
    )

    data class GiveawaysListState(
        val loading: Boolean = true,
        val list: List<GiveawayResponse> = emptyList(),
        val error: String? = null
    )
}