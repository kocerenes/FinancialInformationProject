package com.ekheek.financialinformationproject.domain.use_case.favorite_news

import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.repository.FavoritesArticleRepositoryImpl
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoritesArticleRepositoryImpl
) {
    suspend operator fun invoke(
        favoritesEntity: FavoriteEntity
    ) {
        repository.insertFavoriteArticle(favoritesEntity = favoritesEntity)
    }
}