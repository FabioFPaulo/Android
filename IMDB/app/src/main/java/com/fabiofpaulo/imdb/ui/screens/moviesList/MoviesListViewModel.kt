package com.fabiofpaulo.imdb.ui.screens.moviesList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiofpaulo.imdb.store.domain.repository.TmdbRepository
import com.fabiofpaulo.imdb.util.Event
import com.fabiofpaulo.imdb.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val tmdbRepository: TmdbRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MoviesListState())
    val state = _state.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            tmdbRepository.getRatedMovies()
                .onRight { response ->
                    Log.i("Hellooooo", response.toString())
                    _state.update {
                        it.copy(isLoading = false, data = response)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message, isLoading = false)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }
}