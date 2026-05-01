package com.codewithmuddasir.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codewithmuddasir.newsapp.ui.screens.BookmarksScreen
import com.codewithmuddasir.newsapp.ui.screens.DetailScreen
import com.codewithmuddasir.newsapp.ui.screens.HomeScreen
import com.codewithmuddasir.newsapp.viewmodel.NewsViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier   // ← ADD karo
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier          // ← ADD karo
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel)
        }
        composable(Screen.Bookmarks.route) {
            BookmarksScreen(navController, viewModel)
        }
        composable(Screen.Detail.route) { backStack ->
            val encodedUrl = backStack.arguments?.getString("url") ?: ""
            DetailScreen(
                encodedUrl,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}