package com.ekheek.financialinformationproject.domain.repository

import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesArticleRepository {

    fun readFavoriteArticles(): Flow<List<FavoriteEntity>>
    suspend fun insertFavoriteArticle(favoritesEntity: FavoriteEntity)
    suspend fun deleteFavoriteArticle(favoritesEntity: FavoriteEntity)
    suspend fun deleteAllFavoriteArticles()
}