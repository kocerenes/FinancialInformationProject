package com.ekheek.financialinformationproject.presentation.favorite

import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity

sealed class NewsEvent {
    data class ShowUndoDeleteItemMessage(val favoriteEntity: FavoriteEntity) : NewsEvent()
}