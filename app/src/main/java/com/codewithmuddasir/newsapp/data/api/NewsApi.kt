package com.codewithmuddasir.newsapp.data.api

import com.codewithmuddasir.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String = "general",
        @Query("apiKey") apiKey: String = "YOUR_API_KEY_HERE",
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "YOUR_API_KEY_HERE",
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse
}