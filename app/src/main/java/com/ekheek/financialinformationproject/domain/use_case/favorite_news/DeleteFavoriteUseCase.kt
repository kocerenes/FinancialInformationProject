package com.ekheek.financialinformationproject.domain.use_case.favorite_news

import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.data.repository.FavoritesArticleRepositoryImpl
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoritesArticleRepositoryImpl
) {
    suspend operator fun invoke(
        article: Article
    ) {
        repository.deleteFavoriteArticle(article = article)
    }
}