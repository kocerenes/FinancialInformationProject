package com.ekheek.financialinformationproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse
import com.ekheek.financialinformationproject.domain.use_case.get_news.GetNewsUseCase
import com.ekheek.financialinformationproject.domain.use_case.search_news.SearchNewsUseCase
import com.ekheek.financialinformationproject.util.DataState
import com.ekheek.financialinformationproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private val _news: MutableStateFlow<DataState<ArticleResponse?>> =
        MutableStateFlow(DataState.Empty)
    val news: StateFlow<DataState<ArticleResponse?>>
        get() = _news

    fun searchNews(q: String) {
        searchNewsUseCase(q = q)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _news.value = DataState.Loading
                    }
                    is Resource.Success -> {
                        _news.value = DataState.Success(resource.data)
                    }
                    is Resource.Error -> {
                        _news.value = DataState.Failure(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }

    var category: String = "business"

    fun getNews() {
        getNewsUseCase(category = category)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _news.value = DataState.Loading
                    }
                    is Resource.Success -> {
                        _news.value = DataState.Success(resource.data)
                    }
                    is Resource.Error -> {
                        _news.value = DataState.Failure(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }
}