package com.runeslice.components.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.ButtonType
import com.runeslice.components.modules.ListItemSavedUser
import com.runeslice.components.modules.PrimaryButton
import com.runeslice.models.User
import com.runeslice.services.viewModels.AppStateViewModel

@Composable
fun SavedScreen(viewModel: AppStateViewModel, navController: NavController) {
    val appState by viewModel.appState.collectAsState()
    viewModel.loadSavedUsers()

    fun SearchSavedUser(user: User) {
        viewModel.setSearchedUser(user);
        navController.navigate("user/skills")
    }

    ScreenContainer(pl = 20, pt = 20, pr = 20, pb = 20) {
        if (appState.savedUsers.size > 0) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    LazyColumn {
                        items(appState.savedUsers.size) { index ->
                            ListItemSavedUser(
                                appState.savedUsers[index],
                                { SearchSavedUser(appState.savedUsers[index]) }
                            )
                        }
                    }
                }
                PrimaryButton(
                    type = ButtonType.SECONDARY,
                    onClick = { navController.navigate("compare/skills") },
                    buttonText = "Compare saved users"
                )
            }
        } else {
            Text(text = "No saved users")
        }
    }
}