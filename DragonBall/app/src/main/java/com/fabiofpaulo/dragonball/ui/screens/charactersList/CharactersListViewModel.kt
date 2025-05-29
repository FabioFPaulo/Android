package com.fabiofpaulo.dragonball.ui.screens.charactersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiofpaulo.dragonball.store.domain.repository.DragonBallRepository
import com.fabiofpaulo.dragonball.util.Event
import com.fabiofpaulo.dragonball.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val dragonBallRepository: DragonBallRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CharactersListState())
    val state = _state.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            dragonBallRepository.getCharacters()
                .onRight { response ->
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