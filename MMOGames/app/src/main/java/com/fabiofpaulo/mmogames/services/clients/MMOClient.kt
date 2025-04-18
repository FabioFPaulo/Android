package com.fabiofpaulo.mmogames.services.clients

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MMOClient {
    private const val BASE_URL = "https://www.mmobomb.com/api1/"

    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}