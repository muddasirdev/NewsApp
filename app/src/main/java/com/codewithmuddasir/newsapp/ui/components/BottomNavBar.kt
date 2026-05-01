package com.codewithmuddasir.newsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codewithmuddasir.newsapp.ui.navigation.Screen

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        Triple(Screen.Home, "Home", Icons.Default.Home),
        Triple(Screen.Bookmarks, "Bookmarks", Icons.Default.Bookmark)
    )

    NavigationBar {
        val currentRoute by navController.currentBackStackEntryAsState()
        val route = currentRoute?.destination?.route

        items.forEach { (screen, label, icon) ->
            NavigationBarItem(
                selected = route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}