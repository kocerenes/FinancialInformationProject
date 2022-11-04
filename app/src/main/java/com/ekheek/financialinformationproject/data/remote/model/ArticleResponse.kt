package com.ekheek.financialinformationproject.data.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)