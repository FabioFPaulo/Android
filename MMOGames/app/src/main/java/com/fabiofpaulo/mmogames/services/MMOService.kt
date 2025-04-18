package com.fabiofpaulo.mmogames.services

import com.fabiofpaulo.mmogames.models.GameDetailsResponse
import com.fabiofpaulo.mmogames.models.GameResponse
import com.fabiofpaulo.mmogames.models.GiveawayResponse
import com.fabiofpaulo.mmogames.services.clients.MMOClient
import retrofit2.http.GET
import retrofit2.http.Query

interface IMMOService {
    @GET("games")
    suspend fun getMMOGamesByPlatform(@Query("platform") platform: String = MMOPlatforms.all.name): List<GameResponse>

    suspend fun getMMOGameDetails(@Query("id") id: Int): GameDetailsResponse

    @GET("giveaways")
    suspend fun getGiveaways(): List<GiveawayResponse>
}

enum class MMOPlatforms {
    pc,
    browser,
    all
}

private val retrofit = MMOClient.getClient()
val mmoApi = retrofit.create(IMMOService::class.java)

