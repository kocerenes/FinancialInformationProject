package com.ekheek.financialinformationproject.data.local

import androidx.room.TypeConverter
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    var gson = Gson()

    @TypeConverter
    fun articleToString(article: Article): String = gson.toJson(article)

    @TypeConverter
    fun stringToArticle(data: String): Article {
        val listType = object : TypeToken<Article>() {}.type
        return gson.fromJson(data, listType)
    }
}