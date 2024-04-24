package com.runeslice.components.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.runeslice.assets.bossImgs
import com.runeslice.models.Boss

@Composable
fun BossListItem(boss: Boss, bossIndex: Int, modifier: Modifier) {
    var image = bossImgs[bossIndex]
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        CircularImage(size = 55, image = image, padding = 8)
        StatNumber(text = boss.num.toString())
        Text(
            text = boss.name,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}