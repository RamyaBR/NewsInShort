package com.news.newsinshort.data.api

import com.news.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
       @Query("country") country: String,
       @Query("apiKey") apiKey: String = "45c7b9d1bbca4ddb84c18b67a9aa25f5"
    ) : Response<NewsResponse>
}

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=45c7b9d1bbca4ddb84c18b67a9aa25f5