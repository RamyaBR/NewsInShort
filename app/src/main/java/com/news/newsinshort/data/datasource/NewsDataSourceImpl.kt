package com.news.newsinshort.data.datasource

import android.util.Log
import com.news.newsinshort.data.api.ApiService
import com.news.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NewsDataSource {
    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
       return apiService.getNewsHeadline(country)
    }

}