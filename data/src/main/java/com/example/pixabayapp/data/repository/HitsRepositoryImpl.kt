package com.example.pixabayapp.data.repository

import com.example.pixabayapp.data.network.SearchService
import com.example.pixabayapp.domain.repository.HitsRepository

class HitsRepositoryImpl(private val service: SearchService) : HitsRepository {

    override suspend fun searchImage(apiKey: String, query: String) = service.search(apiKey, query).body()

}
