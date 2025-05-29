package com.fabiofpaulo.dragonball.ui.screens.charactersDetails

import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacterDetails

data class CharactersDetailsState (
    val isLoading: Boolean = false,
    val data: DragonBallCharacterDetails? = null,
    val error: String? = null
)