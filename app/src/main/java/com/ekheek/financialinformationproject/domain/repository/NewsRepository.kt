package com.ekheek.financialinformationproject.domain.repository

import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse

interface NewsRepository {

    suspend fun getNews(category: String): ArticleResponse
    suspend fun searchNews(q: String): ArticleResponse
}