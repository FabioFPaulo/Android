package com.fabiofpaulo.dragonball.store.domain.model

data class ListResponse<T>(
    val items: List<T>,
    val meta: Meta,
    val links: Links
) {
    companion object {
        fun <T> empty() = ListResponse<T>(
            items = listOf(),
            meta = Meta.empty,
            links = Links.empty
        )
    }
}