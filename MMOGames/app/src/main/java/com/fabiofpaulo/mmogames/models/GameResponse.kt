package com.fabiofpaulo.mmogames.models

data class GameResponse(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val profile_url: String
)