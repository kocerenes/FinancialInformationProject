package com.ekheek.financialinformationproject.util

import com.ekheek.financialinformationproject.BuildConfig

object Constants {

    const val NEWS_BASE_URL = "https://newsapi.org/"
    const val NEWS_END_POINT = "v2/top-headlines"
    const val NEWS_API_KEY = BuildConfig.NEWS_API_KEY
    const val NEWS_COUNTRY_CODE = "tr"

    //Favorites Article
    const val DATABASE_NAME = "news_database"
    const val FAVORITES_ARTICLE_TABLE = "favorites_article_table"
}