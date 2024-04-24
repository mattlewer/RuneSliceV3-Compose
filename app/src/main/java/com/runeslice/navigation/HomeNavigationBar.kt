package com.runeslice.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.runeslice.components.modules.BottomNavigationBar
import com.runeslice.dataClass.BottomNavItem

@Composable
fun HomeNavigationBar(navController: NavController){
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(
                name = "Search",
                route = "home/search",
                icon = Icons.Default.Search,
            ),
            BottomNavItem(
                name = "Saved",
                route = "home/saved",
                icon = Icons.Default.Favorite,
            )
        ),
        navController = navController,
        onItemClick = {
            navController.navigate(it.route) {
                popUpTo("home/search") {
                    saveState = false
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}