package com.runeslice.components.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runeslice.R

@Composable
fun ProgressBar(label: String, position: Float, percentage: String) {
    println("------------ " + position.toString())
    Column {
        Text(text = label, fontSize = 14.sp)
        LinearProgressIndicator(
            progress = position,
            color = colorResource(id = R.color.watermelon_red),
            backgroundColor = colorResource(id = R.color.progress_left),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
        )
        Text(
            text = percentage + "%",
            textAlign = TextAlign.End,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }

}