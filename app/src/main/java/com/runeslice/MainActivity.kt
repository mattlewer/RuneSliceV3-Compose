package com.runeslice

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.runeslice.components.modules.TopBar
import com.runeslice.navigation.CompareNavigationBar
import com.runeslice.navigation.HomeNavigationBar
import com.runeslice.ui.theme.RuneSlice_ComposeTheme
import com.runeslice.navigation.TabNavigation
import com.runeslice.navigation.UserNavigationBar
import com.runeslice.services.viewModels.AppStateViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this;

        val appStateViewModel = AppStateViewModel(context);
        appStateViewModel.loadSavedUsers()

        setContent {
            RuneSlice_ComposeTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry.value?.destination?.route

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = currentRoute != null && currentRoute.contains("home/"),
                            enter = expandVertically(animationSpec = tween(delayMillis = 0)),
                            exit = fadeOut(animationSpec = tween(delayMillis = 300))
                        ) {
                            HomeNavigationBar(navController = navController)
                        }
                        AnimatedVisibility(
                            visible = currentRoute != null && currentRoute.contains("user/") && !currentRoute.contains(
                                "user/skill/"
                            ),
                            enter = expandVertically(animationSpec = tween(delayMillis = 200)),
                            exit = shrinkVertically(animationSpec = tween(delayMillis = 200))
                        ) {
                            UserNavigationBar(navController = navController)
                        }
                        AnimatedVisibility(
                            visible = currentRoute != null && currentRoute.contains("compare/"),
                            enter = expandVertically(animationSpec = tween(delayMillis = 300)),
                            exit = shrinkVertically(animationSpec = tween(delayMillis = 300))
                        ) {
                            CompareNavigationBar(navController = navController)
                        }
                    },
                    topBar = {
                        if (currentRoute != null && currentRoute.contains("user/")) {
                            TopBar(viewModel = appStateViewModel)
                        }
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        TabNavigation(
                            navController = navController,
                            context = context,
                            appStateViewModel
                        )
                    }
                }
            }
        }
    }
}