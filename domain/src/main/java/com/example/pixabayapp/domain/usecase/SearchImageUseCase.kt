package com.example.pixabayapp.domain.usecase

import com.example.pixabayapp.domain.repository.HitsRepository

class SearchImageUseCase(private val repository: HitsRepository) {

    suspend fun execute(apiKey: String, query: String) = repository.searchImage(apiKey, query)
}
