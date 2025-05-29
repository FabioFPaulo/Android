package com.fabiofpaulo.dragonball.store.domain.model

data class DragonBallCharacterDetails (
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val deletedAt: String?,
    val originPlanet: DragonBallPlanet,
    val transformations: List<DragonBallTransformation>
)