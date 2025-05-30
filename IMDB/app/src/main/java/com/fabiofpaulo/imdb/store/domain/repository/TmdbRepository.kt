package com.fabiofpaulo.imdb.store.domain.repository

import arrow.core.Either
import com.fabiofpaulo.imdb.store.domain.model.ListResponse
import com.fabiofpaulo.imdb.store.domain.model.Movie
import com.fabiofpaulo.imdb.store.domain.model.NetworkError

interface TmdbRepository {
    suspend fun getRatedMovies(): Either<NetworkError, ListResponse<Movie>>

    suspend fun getMovieDetails(id: Int): Either<NetworkError, Movie>
}

