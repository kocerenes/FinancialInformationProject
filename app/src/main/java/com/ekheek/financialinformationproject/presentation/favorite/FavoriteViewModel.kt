package com.ekheek.financialinformationproject.presentation.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.repository.FavoritesArticleRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoritesArticleRepositoryImpl
) : ViewModel() {

    var favoriteNews: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()

    fun getFavoriteNews() = viewModelScope.launch {
        repository.readFavoriteArticles().collect {
            favoriteNews.value = it
        }
    }
}