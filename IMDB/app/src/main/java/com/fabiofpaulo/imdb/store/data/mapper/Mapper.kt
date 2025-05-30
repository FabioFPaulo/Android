package com.fabiofpaulo.imdb.store.data.mapper

import com.fabiofpaulo.imdb.store.domain.model.ApiError
import com.fabiofpaulo.imdb.store.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toGeneralError(): NetworkError {
    val error = when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownError
        else -> ApiError.UnknownError
    }

    return NetworkError(
        error = error,
        t = this
    )
}