package com.ekheek.financialinformationproject.domain.use_case.search_news

import com.ekheek.financialinformationproject.data.remote.model.ArticleResponse
import com.ekheek.financialinformationproject.domain.repository.NewsRepository
import com.ekheek.financialinformationproject.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(
        q: String
    ): Flow<Resource<ArticleResponse>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.searchNews(q = q)
            emit(Resource.Success(data = news))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}