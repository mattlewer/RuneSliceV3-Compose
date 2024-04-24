package com.runeslice.components.modules

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.runeslice.R
import com.runeslice.services.viewModels.AppStateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: AppStateViewModel) {

    val appState by viewModel.appState.collectAsState()
    val searchedUser = appState.searchedUser!!
    var isSaved by remember { mutableStateOf(viewModel.isSavedUser(searchedUser)) }

    val savedColor =
        if (isSaved) R.color.watermelon_red else R.color.disabled

    val savedIcon =
        if (isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Surface(elevation = 3.dp){
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = colorResource(id = R.color.dark_blue)
            ),
            title = {
                Text(
                    text = searchedUser.name,
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.toggleSave(searchedUser)
                        isSaved = !isSaved
                    },
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Icon(
                        imageVector = savedIcon,
                        contentDescription = "",
                        tint = colorResource(id = savedColor),
                        modifier = Modifier.size(22.dp)
                    )
                }
            },
        )
    }
}