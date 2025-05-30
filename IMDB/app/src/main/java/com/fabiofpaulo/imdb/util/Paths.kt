package com.fabiofpaulo.imdb.util

import kotlinx.serialization.Serializable

sealed class Paths{
    @Serializable
    object MoviesList: Paths()

    @Serializable
    data class MovieDetails(val id: Int): Paths()
}