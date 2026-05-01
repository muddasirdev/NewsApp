package com.codewithmuddasir.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmuddasir.newsapp.data.model.Article
import com.codewithmuddasir.newsapp.data.repository.NewsRepository
import com.codewithmuddasir.newsapp.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)   // ← yeh add karo
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val categories = listOf(
        "general", "business", "technology",
        "sports", "health", "entertainment"
    )

    private val _selectedCategory = MutableStateFlow("general")

    val headlines = _selectedCategory.flatMapLatest { category ->
        repository.getTopHeadlines(category)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Resource.Loading()
    )

    val bookmarks = repository.getBookmarks()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            repository.toggleBookmark(article)
        }
    }
}