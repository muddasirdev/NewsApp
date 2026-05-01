package com.codewithmuddasir.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.codewithmuddasir.newsapp.ui.components.NewsCard
import com.codewithmuddasir.newsapp.ui.navigation.Screen
import com.codewithmuddasir.newsapp.viewmodel.NewsViewModel

@Composable
fun BookmarksScreen(
    navController: NavHostController,
    viewModel: NewsViewModel
) {
    val bookmarks by viewModel.bookmarks.collectAsState()

    if (bookmarks.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No bookmarks yet!\nSave articles to read later.",
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn {
            items(bookmarks) { article ->
                NewsCard(
                    article = article,
                    onBookmarkClick = {
                        viewModel.toggleBookmark(article)
                    },
                    onClick = {
                        navController.navigate(
                            Screen.Detail.createRoute(article.url)
                        )
                    }
                )
            }
        }
    }
}