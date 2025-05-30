package com.fabiofpaulo.imdb.store.data.repository

import arrow.core.Either
import com.fabiofpaulo.imdb.store.data.mapper.toGeneralError
import com.fabiofpaulo.imdb.store.data.remote.TmdbApi
import com.fabiofpaulo.imdb.store.domain.model.ListResponse
import com.fabiofpaulo.imdb.store.domain.model.Movie
import com.fabiofpaulo.imdb.store.domain.model.NetworkError
import com.fabiofpaulo.imdb.store.domain.repository.TmdbRepository
import javax.inject.Inject

class TmdbRepositoryImpl @Inject constructor(
    private val tmdbApi: TmdbApi
): TmdbRepository {
    override suspend fun getRatedMovies(): Either<NetworkError, ListResponse<Movie>> {
        return Either.Companion.catch {
            tmdbApi.getRatedMovies()
        }.mapLeft { it.toGeneralError() }
    }

    override suspend fun getMovieDetails(id: Int): Either<NetworkError, Movie> {
        return Either.Companion.catch {
            tmdbApi.getMovieDetails(id)
        }.mapLeft { it.toGeneralError() }
    }
}

