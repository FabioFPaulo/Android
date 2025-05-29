package com.fabiofpaulo.dragonball.store.domain.model

data class Links(
    val first: String,
    val previous: String,
    val next: String,
    val last: String
) {
    companion object {
        val empty = Links("", "", "", "")
    }
}