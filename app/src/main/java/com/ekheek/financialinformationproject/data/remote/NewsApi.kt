package com.ekheek.financialinformationproject.data.remote

import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse
import com.ekheek.financialinformationproject.util.Constants.NEWS_API_KEY
import com.ekheek.financialinformationproject.util.Constants.NEWS_COUNTRY_CODE
import com.ekheek.financialinformationproject.util.Constants.NEWS_END_POINT
import com.ekheek.financialinformationproject.util.Constants.SEARCH_NEWS_END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(NEWS_END_POINT)
    suspend fun getNews(
        @Query("country") countryCode: String = NEWS_COUNTRY_CODE,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): ArticleResponse

    @GET(SEARCH_NEWS_END_POINT)
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): ArticleResponse
}