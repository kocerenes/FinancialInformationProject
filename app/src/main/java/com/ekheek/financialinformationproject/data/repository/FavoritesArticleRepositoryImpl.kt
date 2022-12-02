package com.ekheek.financialinformationproject.data.repository

import com.ekheek.financialinformationproject.data.local.ArticleDao
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.domain.repository.FavoritesArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesArticleRepositoryImpl @Inject constructor(
    private val dao: ArticleDao
) : FavoritesArticleRepository {

    override fun readFavoriteArticles(): Flow<List<FavoriteEntity>> = dao.readFavoriteArticles()

    override suspend fun insertFavoriteArticle(favoritesEntity: FavoriteEntity) {
        dao.insertFavoriteArticle(favoritesEntity = favoritesEntity)
    }

    override suspend fun deleteFavoriteArticle(article: Article) {
        dao.deleteFavoriteArticle(article = article)
    }

    override suspend fun deleteAllFavoriteArticles() {
        dao.deleteAllFavoriteArticles()
    }
}