package com.fabiofpaulo.dragonball.util

import kotlinx.serialization.Serializable

sealed class Paths{
    @Serializable
    object CharacterList: Paths()

    @Serializable
    data class CharacterDetails(val characterId: Int): Paths()
}