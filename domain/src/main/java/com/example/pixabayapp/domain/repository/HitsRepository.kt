package com.example.pixabayapp.domain.repository

import com.example.pixabayapp.domain.models.SearchResponse

interface HitsRepository {

    suspend fun searchImage(apiKey: String, query: String): SearchResponse?
}
