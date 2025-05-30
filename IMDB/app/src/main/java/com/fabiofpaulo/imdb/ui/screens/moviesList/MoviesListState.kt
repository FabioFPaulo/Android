package com.fabiofpaulo.imdb.ui.screens.moviesList

import com.fabiofpaulo.imdb.store.domain.model.ListResponse
import com.fabiofpaulo.imdb.store.domain.model.Movie

data class MoviesListState(
    val isLoading: Boolean = false,
    val data: ListResponse<Movie> = ListResponse.empty<Movie>(),
    val error: String? = null
)

