package com.fabiofpaulo.dragonball.store.domain.model

data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
) {
    companion object {
        val empty = Meta(
            0, 0, 0, 0, 0
        )
    }
}