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
import com.runeslice.R
import com.runeslice.assets.scrollImgs
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.CircularImage
import com.runeslice.components.modules.DropdownSelect
import com.runeslice.components.modules.ListItemRankedUser
import com.runeslice.components.modules.Stat
import com.runeslice.services.viewModels.AppStateViewModel

@Composable
fun CompareCluesScreen(viewModel: AppStateViewModel, navController: NavController) {
    val appState by viewModel.appState.collectAsState()
    var selectedIndex by remember { mutableStateOf(0) }

    var context = LocalContext.current
    val clueNames = context.resources.getStringArray(R.array.clues)

    val sortedListDescending =
        appState.savedUsers.sortedByDescending { it.clues[selectedIndex].num }

    ScreenContainer(pl = 20, pt = 20, pr = 20) {
        CircularImage(size = 80, image = scrollImgs[selectedIndex])
        DropdownSelect(
            options = clueNames,
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
                            "Completed",
                            "%,d".format(sortedListDescending[index].clues[selectedIndex].num)
                        )
                    }
                )
            }
        }
    }
}