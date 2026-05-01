package com.codewithmuddasir.newsapp.data.repository

import com.codewithmuddasir.newsapp.data.api.NewsApi
import com.codewithmuddasir.newsapp.data.db.ArticleDao
import com.codewithmuddasir.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val dao: ArticleDao
) {
    fun getTopHeadlines(category: String): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getTopHeadlines(category = category)
            val articles = response.articles.mapNotNull { dto ->
                dto.url?.let { url ->
                    Article(
                        url = url,
                        title = dto.title ?: "No Title",
                        description = dto.description,
                        urlToImage = dto.urlToImage,
                        publishedAt = dto.publishedAt ?: "",
                        source = dto.source?.name ?: "Unknown",
                        content = dto.content
                    )
                }
            }
            dao.deleteNonBookmarked()
            dao.insertArticles(articles)
        } catch (e: Exception) {
            emit(Resource.Error("No internet. Showing cached news."))
        }

        dao.getAllArticles().collect { emit(Resource.Success(it)) }
    }

    fun getBookmarks(): Flow<List<Article>> = dao.getBookmarkedArticles()

    suspend fun toggleBookmark(article: Article) {
        val existing = dao.getArticleByUrl(article.url)
        if (existing != null) {
            dao.updateArticle(existing.copy(isBookmarked = !existing.isBookmarked))
        } else {
            dao.insertArticles(listOf(article.copy(isBookmarked = true)))
        }
    }
}