package com.ekheek.financialinformationproject.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    /** For Favorite Article **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteArticle(favoritesEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites_article_table ORDER BY id ASC")
    fun readFavoriteArticles(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavoriteArticle(favoritesEntity: FavoriteEntity)

    @Query("DELETE FROM favorites_article_table")
    suspend fun deleteAllFavoriteArticles()
}