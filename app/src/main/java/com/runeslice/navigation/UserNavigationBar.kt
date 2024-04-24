package com.runeslice.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.runeslice.components.modules.BottomNavigationBar
import com.runeslice.dataClass.BottomNavItem

@Composable
fun UserNavigationBar(navController: NavController) {
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(
                name = "Skills",
                route = "user/skills",
                icon = Icons.Default.List,
            ),
            BottomNavItem(
                name = "Boss Kills",
                route = "user/bosses",
                icon = Icons.Default.Face,
            ),
            BottomNavItem(
                name = "Clue Scrolls",
                route = "user/clues",
                icon = Icons.Default.Check,
            )
        ),
        navController = navController,
        onItemClick = {
            navController.navigate(it.route) {
                popUpTo("user/skills") {
                    saveState = false
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}