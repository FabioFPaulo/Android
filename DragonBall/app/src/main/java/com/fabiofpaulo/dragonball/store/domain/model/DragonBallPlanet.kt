package com.fabiofpaulo.dragonball.store.domain.model

data class DragonBallPlanet (
    val id: Int,
    val name: String,
    val isDistroyed: Boolean,
    val description: String,
    val image: String,
    val deletedAt: String?
)
