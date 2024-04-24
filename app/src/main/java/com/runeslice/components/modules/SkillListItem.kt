package com.runeslice.components.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.runeslice.assets.skillImgs
import com.runeslice.models.Skill

@Composable
fun SkillListItem(skill: Skill, skillIndex: Int, modifier: Modifier) {
    var image = skillImgs[skillIndex]
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        CircularImage(size = 50, image = image, padding = 12)
        StatNumber(text = skill.level.toString())
    }
}