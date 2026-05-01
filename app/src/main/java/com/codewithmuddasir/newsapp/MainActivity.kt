package com.codewithmuddasir.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.codewithmuddasir.newsapp.ui.components.BottomNavBar
import com.codewithmuddasir.newsapp.ui.navigation.AppNavigation
import com.codewithmuddasir.newsapp.ui.theme.NewsAppTheme
import com.codewithmuddasir.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                val viewModel: NewsViewModel = hiltViewModel()

                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { paddingValues ->
                    AppNavigation(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(paddingValues) // ← Yeh zaroor do
                    )
                }
            }
        }
    }
}