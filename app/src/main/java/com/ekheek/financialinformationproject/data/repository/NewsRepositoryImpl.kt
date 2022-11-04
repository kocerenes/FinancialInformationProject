package com.ekheek.financialinformationproject.data.repository

import com.ekheek.financialinformationproject.data.remote.NewsApi
import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse
import com.ekheek.financialinformationproject.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(
        category: String
    ): ArticleResponse {
        return newsApi.getNews(category = category)
    }
}