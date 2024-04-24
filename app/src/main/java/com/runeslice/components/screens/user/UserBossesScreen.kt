package com.runeslice.components.screens.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.BossListItem
import com.runeslice.services.viewModels.AppStateViewModel

@Composable
fun UserBossesScreen(
    viewModel: AppStateViewModel,
    navController: NavController,
) {
    val appState by viewModel.appState.collectAsState()
    val user = appState.searchedUser
    ScreenContainer{
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            content = {
                items(user!!.boss.size) { index ->
                    BossListItem(
                        boss = user.boss[index],
                        bossIndex = index,
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .clickable(onClick = {

                            })
                    )
                }
            }
        )
    }
}