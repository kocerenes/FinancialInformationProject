package com.ekheek.financialinformationproject.data.remote

import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse
import com.ekheek.financialinformationproject.util.Constants.NEWS_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(NEWS_END_POINT)
    suspend fun getNews(
        @Query("country") countryCode: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): ArticleResponse
}