package com.fabiofpaulo.imdb.ui.screens.movieDetails

import com.fabiofpaulo.imdb.store.domain.model.Movie

data class MoviewDetailsState (
    val isLoading: Boolean = false,
    val data: Movie? = null,
    val error: String? = null
)