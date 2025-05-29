package com.fabiofpaulo.dragonball.store.data.remote

import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacter
import com.fabiofpaulo.dragonball.store.domain.model.DragonBallCharacterDetails
import com.fabiofpaulo.dragonball.store.domain.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DragonBallApi {
    @GET("characters")
    suspend fun getCharacters(): ListResponse<DragonBallCharacter>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Int ): DragonBallCharacterDetails
}