package com.example.pixabayapp.domain.models

data class SearchResponse(
    val hits: List<SearchHit>,
    val total: Int,
    val totalHits: Int
)
