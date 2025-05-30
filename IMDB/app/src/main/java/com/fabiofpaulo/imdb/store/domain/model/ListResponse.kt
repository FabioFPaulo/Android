package com.fabiofpaulo.imdb.store.domain.model

data class ListResponse<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int,
){
    companion object {
        fun <T> empty() = ListResponse<T>(
            0,
            emptyList(),
            0,
            0
        )
    }
}