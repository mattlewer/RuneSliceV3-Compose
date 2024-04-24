package com.runeslice.components.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.runeslice.R
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.ButtonType
import com.runeslice.components.modules.PrimaryButton
import com.runeslice.components.modules.TextInputField
import com.runeslice.components.modules.WelcomeTextAndLogo
import com.runeslice.dataClass.SearchState
import com.runeslice.services.viewModels.AppStateViewModel

@Composable
fun SearchScreen(
    appViewModel: AppStateViewModel,
    navController: NavController,
) {
    val appState by appViewModel.appState.collectAsState()

    var enteredText = remember {mutableStateOf("")}

    if(appState.searchState == SearchState.SEARCHED){
        navController.navigate("user/skills")
        appState.searchState = SearchState.IDLE
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ScreenContainer {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = 50.dp, start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.spacedBy(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                WelcomeTextAndLogo()
                TextInputField(
                    enteredText.value,
                    "Username",
                    { enteredText.value = it }
                )
                PrimaryButton(
                    type = ButtonType.PRIMARY,
                    onClick = { appViewModel.searchUser(enteredText.value) },
                    buttonText = "Search user"
                )
            }

        }
        if (appState.searchState == SearchState.LOADING) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = colorResource(id = R.color.watermelon_red),
                    trackColor = colorResource(id = R.color.disabled),
                )
            }
        }
    }
}