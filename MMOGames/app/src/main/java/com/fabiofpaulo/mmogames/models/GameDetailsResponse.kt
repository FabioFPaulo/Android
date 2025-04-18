package com.fabiofpaulo.mmogames.models

data class GameDetailsResponse (
    val id: Int,
    val title: String,
    val thumbnail: String,
    val status: String,
    val short_description: String,
    val description: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val release_date: String,
    val profile_url: String,
    val minimum_system_requirements: GameRequirements,
    val screenshots: List<GameScreenshot>
)