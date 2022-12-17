package com.ekheek.financialinformationproject.util

import com.ekheek.financialinformationproject.BuildConfig

object Constants {

    const val NEWS_BASE_URL = "https://newsapi.org/"
    const val NEWS_END_POINT = "v2/top-headlines"
    const val NEWS_API_KEY = BuildConfig.NEWS_API_KEY
    const val NEWS_COUNTRY_CODE = "us"
    const val SEARCH_NEWS_END_POINT = "v2/everything"

    //Favorites Article
    const val DATABASE_NAME = "news_database"
    const val FAVORITES_ARTICLE_TABLE = "favorites_article_table"

    object Images {
        const val logo =
            "https://github.com/herdal06/FinancialImages/blob/main/app_name_hek_news.png?raw=true"
        const val loginPNG =
            "https://github.com/herdal06/FinancialImages/blob/main/login2.png?raw=true"
        const val registerPNG =
            "https://github.com/herdal06/FinancialImages/blob/main/sign_up.png?raw=true"
    }
}