package com.ekheek.financialinformationproject.domain.repository

import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse

interface NewsRepository {

    suspend fun getNews(countryCode: String, category: String, apiKey: String): ArticleResponse
}