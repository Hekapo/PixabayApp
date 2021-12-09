package com.example.pixabayapp.data.network

import com.example.pixabayapp.domain.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    //    val url = "https://pixabay.com/"
    @GET("api/")
    suspend fun search(@Query("key") apiKey: String, @Query("q") query: String): Response<SearchResponse>
}
