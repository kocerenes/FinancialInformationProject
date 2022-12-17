package com.ekheek.financialinformationproject.presentation.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.repository.FavoritesArticleRepositoryImpl
import com.ekheek.financialinformationproject.domain.use_case.favorite_news.AddFavoriteUseCase
import com.ekheek.financialinformationproject.domain.use_case.favorite_news.DeleteFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoritesArticleRepositoryImpl,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {

    var favoriteNews: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()

    fun getFavoriteNews() = viewModelScope.launch {
        repository.readFavoriteArticles().collect {
            favoriteNews.value = it
        }
    }

    private val newsEventChannel = Channel<NewsEvent>()
    val productsEvent = newsEventChannel.receiveAsFlow()

    fun onItemSwiped(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(favoriteEntity.article)
        newsEventChannel.send(NewsEvent.ShowUndoDeleteItemMessage(favoriteEntity))
    }

    fun onUndoDeleteClick(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        addFavoriteUseCase.invoke(favoriteEntity)
    }
}