package com.news.newsinshort.data.datasource

import com.news.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.Query

interface NewsDataSource {

    suspend fun getNewsHeadline(country: String) : Response<NewsResponse>
}