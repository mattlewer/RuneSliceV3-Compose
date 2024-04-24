package com.runeslice.components.screens.compare

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.runeslice.assets.skillImgs
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.CircularImage
import com.runeslice.components.modules.DropdownSelect
import com.runeslice.components.modules.ListItemRankedUser
import com.runeslice.components.modules.Stat
import com.runeslice.services.viewModels.AppStateViewModel
import com.runeslice.R

@Composable
fun CompareSkillsScreen(viewModel: AppStateViewModel, navController: NavController) {

    val appState by viewModel.appState.collectAsState()
    var selectedIndex by remember { mutableStateOf(0) }

    var context = LocalContext.current
    val skillNames = context.resources.getStringArray(R.array.skills)

    val sortedListDescending =
        appState.savedUsers.sortedByDescending { it.skills[selectedIndex].xp }

    ScreenContainer(pl = 20, pt = 20, pr = 20) {
        CircularImage(size = 80, image = skillImgs[selectedIndex])
        DropdownSelect(
            options = skillNames,
            setSelected = { index -> selectedIndex = index },
            selectedIndex
        )
        LazyColumn {
            items(sortedListDescending.size) { index ->
                ListItemRankedUser(
                    username = sortedListDescending[index].name,
                    position = index,
                    content = {
                        Stat(
                            "Level",
                            "%,d".format(sortedListDescending[index].skills[selectedIndex].level)
                        )
                        Stat(
                            "XP",
                            "%,d".format(sortedListDescending[index].skills[selectedIndex].xp)
                        )
                    }
                )
            }
        }
    }
}