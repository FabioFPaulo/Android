package com.fabiofpaulo.mmogames.models

data class GiveawayResponse (
    val id: Int,
    val title: String,
    val keys_left: String,
    val thumbnail: String,
    val main_image: String,
    val short_description: String,
    val giveaway_url: String
)