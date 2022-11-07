package com.ekheek.financialinformationproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.util.Constants.FAVORITES_ARTICLE_TABLE

@Entity(tableName = FAVORITES_ARTICLE_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var article: Article
)
