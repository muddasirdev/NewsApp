package com.codewithmuddasir.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithmuddasir.newsapp.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}