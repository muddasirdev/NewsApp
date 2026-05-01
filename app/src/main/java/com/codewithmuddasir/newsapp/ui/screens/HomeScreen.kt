package com.codewithmuddasir.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryScrollableTabRow  // ← Updated
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codewithmuddasir.newsapp.data.repository.Resource
import com.codewithmuddasir.newsapp.ui.components.NewsCard
import com.codewithmuddasir.newsapp.ui.navigation.Screen
import com.codewithmuddasir.newsapp.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: NewsViewModel
) {
    val headlines by viewModel.headlines.collectAsState()
    val categories = viewModel.categories
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {

        // App Title
        TopAppBar(
            title = {
                Text(
                    text = "📰 News App",
                    fontWeight = FontWeight.Bold
                )
            }
        )

        // Category Tabs — updated to PrimaryScrollableTabRow
        PrimaryScrollableTabRow(
            selectedTabIndex = selectedIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        viewModel.selectCategory(category)
                    },
                    text = {
                        Text(category.replaceFirstChar { it.uppercase() })
                    }
                )
            }
        }

        // Content
        when (val state = headlines) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⚠️ ${state.message}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            is Resource.Success -> {
                if (state.data.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No news found")
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            items = state.data,
                            key = { it.url }   // ← Speed ke liye key add karo
                        ) { article ->
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
        }
    }
}