package com.codewithmuddasir.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.codewithmuddasir.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE isBookmarked = 1")
    fun getBookmarkedArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Update
    suspend fun updateArticle(article: Article)

    @Query("DELETE FROM articles WHERE isBookmarked = 0")
    suspend fun deleteNonBookmarked()

    @Query("SELECT * FROM articles WHERE url = :url")
    suspend fun getArticleByUrl(url: String): Article?
}