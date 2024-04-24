package com.runeslice.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.runeslice.components.screens.compare.CompareBossesScreen
import com.runeslice.components.screens.compare.CompareCluesScreen
import com.runeslice.components.screens.compare.CompareSkillsScreen
import com.runeslice.components.screens.main.SavedScreen
import com.runeslice.components.screens.main.SearchScreen
import com.runeslice.components.screens.user.SingleSkillScreen
import com.runeslice.components.screens.user.UserSkillsScreen
import com.runeslice.components.screens.user.UserBossesScreen
import com.runeslice.components.screens.user.UserCluesScreen
import com.runeslice.services.viewModels.AppStateViewModel

@Composable
fun TabNavigation(
    navController: NavHostController,
    context: Context,
    viewModel: AppStateViewModel
) {
    NavHost(navController = navController, startDestination = "home",
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        navigation(startDestination = "home/search", route = "home") {
            composable("home/search") {
                SearchScreen(viewModel, navController)
            }
            composable("home/saved") {
                SavedScreen(viewModel, navController)
            }

            composable("user/skills") {
                UserSkillsScreen(viewModel, navController)
            }
            composable("user/bosses") {
                UserBossesScreen(viewModel, navController)
            }
            composable("user/clues") {
                UserCluesScreen(viewModel, navController)
            }
            composable("user/skill/{skill_index}", arguments = listOf(
                navArgument("skill_index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )) {
                SingleSkillScreen(viewModel, navController, it.arguments!!.getInt("skill_index"))
            }

            composable("compare/skills") {
                CompareSkillsScreen(viewModel, navController)
            }
            composable("compare/bosses") {
                CompareBossesScreen(viewModel, navController)
            }
            composable("compare/clues") {
                CompareCluesScreen(viewModel, navController)
            }
        }
    }
    }