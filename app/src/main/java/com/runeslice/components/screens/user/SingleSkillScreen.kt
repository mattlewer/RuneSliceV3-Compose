package com.runeslice.components.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.runeslice.R
import com.runeslice.assets.skillImgs
import com.runeslice.components.ScreenContainer
import com.runeslice.components.modules.CircularImage
import com.runeslice.components.modules.ProgressBar
import com.runeslice.components.modules.Stat
import com.runeslice.services.viewModels.AppStateViewModel
import com.runeslice.util.calculatePercentThroughLevel
import com.runeslice.util.calculatePercentToNextTen
import com.runeslice.util.calculatePercentToNinetyNine

@Composable
fun SingleSkillScreen(
    viewModel: AppStateViewModel,
    navController: NavController,
    skillIndex: Int
) {
    val appState by viewModel.appState.collectAsState()
    val user = appState.searchedUser!!
    val currentSkill = user.skills[skillIndex]

    val percentageThroughLevel = calculatePercentThroughLevel(skill = currentSkill)
    val percentageToNextTen = calculatePercentToNextTen(skill = currentSkill)
    val percentageToNinetyNine = calculatePercentToNinetyNine(skill = currentSkill)

    ScreenContainer(pl = 20, pt = 20, pr = 20, pb = 20) {
        CircularImage(size = 100, image = skillImgs[skillIndex])
        Text(
            text = currentSkill.name,
            color = colorResource(id = R.color.watermelon_red),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
        )
        Stat("Level", currentSkill.level.toString())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Stat("XP", "%,d".format(currentSkill.xp))
            Stat("Rank", currentSkill.level.toString())
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 40.dp, bottom = 100.dp, start = 10.dp, end = 10.dp)

        ) {
            ProgressBar(
                label = "To next level:",
                position = percentageThroughLevel,
                percentage = (percentageThroughLevel * 100).toString()
            )
            ProgressBar(
                label = "To next multiple of 10:",
                position = percentageToNextTen,
                percentage = (percentageToNextTen * 100).toString()
            )
            ProgressBar(
                label = "To 99:",
                position = percentageToNinetyNine,
                percentage = (percentageToNinetyNine * 100).toString()
            )
        }
    }
}