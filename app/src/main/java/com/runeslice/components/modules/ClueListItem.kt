package com.runeslice.components.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.runeslice.assets.scrollImgs
import com.runeslice.models.Clue

@Composable
fun ClueListItem(clue: Clue, clueIndex: Int, modifier: Modifier) {
    var image = scrollImgs[clueIndex]
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        CircularImage(size = 80, image = image)
        StatNumber(fontSize = 18, clue.num.toString())
        Text(
            text = clue.name,
            fontSize = 14.sp
        )
    }
}