package com.codewithmuddasir.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    val url: String,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val source: String,
    val content: String?,
    val isBookmarked: Boolean = false
)