package com.ekheek.financialinformationproject.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.data.repository.FavoritesArticleRepositoryImpl
import com.ekheek.financialinformationproject.domain.use_case.favorite_news.AddFavoriteUseCase
import com.ekheek.financialinformationproject.domain.use_case.favorite_news.DeleteFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val repositoryImpl: FavoritesArticleRepositoryImpl
) : ViewModel() {

    var favLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        favLiveData.value = false
    }

    fun addFavoriteNews(favoritesEntity: FavoriteEntity) = viewModelScope.launch {
        addFavoriteUseCase(favoritesEntity = favoritesEntity)
    }

    fun deleteFavoriteNews(article: Article) = viewModelScope.launch {
        deleteFavoriteUseCase(article = article)
    }

    fun isFav(article: Article) = viewModelScope.launch {
        favLiveData.value = false
        repositoryImpl.readFavoriteArticles().collect {
            it.forEach { favoritesNews ->
                if (article.title.equals(favoritesNews.article.title)) {
                    favLiveData.value = true
                }
            }
        }
    }
}