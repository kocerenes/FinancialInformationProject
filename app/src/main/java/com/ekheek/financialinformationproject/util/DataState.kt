package com.ekheek.financialinformationproject.util

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    object Empty : DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Failure(val error: String?) : DataState<Nothing>()
}