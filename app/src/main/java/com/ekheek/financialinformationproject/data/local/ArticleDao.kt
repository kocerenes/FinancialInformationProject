package com.ekheek.financialinformationproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    /** For Favorite Article **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteArticle(favoritesEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites_article_table ORDER BY id DESC")
    fun readFavoriteArticles(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM favorites_article_table WHERE article=:article")
    suspend fun deleteFavoriteArticle(article: Article)

    @Query("DELETE FROM favorites_article_table")
    suspend fun deleteAllFavoriteArticles()
}