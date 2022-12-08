package com.ekheek.financialinformationproject.domain.repository

import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

interface FavoritesArticleRepository {

    fun readFavoriteArticles(): Flow<List<FavoriteEntity>>
    suspend fun insertFavoriteArticle(favoritesEntity: FavoriteEntity)
    suspend fun deleteFavoriteArticle(article: Article)
    suspend fun deleteAllFavoriteArticles()
}