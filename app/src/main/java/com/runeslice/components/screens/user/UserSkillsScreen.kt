package com.runeslice.components.screens.user

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.runeslice.assets.fakeData.makeFakeUser
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.SkillListItem
import com.runeslice.services.viewModels.AppStateViewModel

@Composable
fun UserSkillsScreen(
    viewModel: AppStateViewModel,
    navController: NavController,
) {
    val appState by viewModel.appState.collectAsState()
    val user = appState.searchedUser ?: makeFakeUser()

    ScreenContainer(pt = 20, pb = 10){
        Text(text = "Tap a skill icon to find out more!", fontSize = 12.sp)
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp),
            content = {
                items(user.skills.size) { index ->
                    SkillListItem(
                        skill = user.skills[index],
                        skillIndex = index,
                        modifier = Modifier.clickable(onClick = {
                            navController.navigate("user/skill/$index");
                        })
                    )
                }
            }
        )
    }
}