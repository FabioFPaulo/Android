package com.fabiofpaulo.imdb.store.data.remote

import com.fabiofpaulo.imdb.store.domain.model.ListResponse
import com.fabiofpaulo.imdb.store.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface TmdbApi {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOGVkZjk1ODE1ODNmNTI0MWM1Njk1MTY2NTdjOTlkNiIsIm5iZiI6MTc0ODYyNTczMS4xNTM5OTk4LCJzdWIiOiI2ODM5ZTk0MzgwOGVlZDdmNGQ5NjkwM2YiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.9jk_j3f6XP_XTUmqfgcy0Zk-2KygSsxOnvN0e-KDBKM")
    @GET("movie/top_rated")
    suspend fun getRatedMovies(): ListResponse<Movie>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOGVkZjk1ODE1ODNmNTI0MWM1Njk1MTY2NTdjOTlkNiIsIm5iZiI6MTc0ODYyNTczMS4xNTM5OTk4LCJzdWIiOiI2ODM5ZTk0MzgwOGVlZDdmNGQ5NjkwM2YiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.9jk_j3f6XP_XTUmqfgcy0Zk-2KygSsxOnvN0e-KDBKM")
    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int ): Movie

}