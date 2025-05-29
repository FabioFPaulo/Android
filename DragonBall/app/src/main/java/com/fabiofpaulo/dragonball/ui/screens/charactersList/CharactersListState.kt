package com.fabiofpaulo.dragonball.ui.screens.charactersList

import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacter
import com.fabiofpaulo.dragonball.store.domain.model.ListResponse

data class CharactersListState(
    val isLoading: Boolean = false,
    val data: ListResponse<DragonBallCharacter> = ListResponse.empty(),
    val error: String? = null
)