package com.runeslice.components.modules

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runeslice.R

@Composable
fun StatNumber(fontSize: Int = 14, text: String){
    Text(
        text = text,
        color = colorResource(id = R.color.watermelon_red),
        modifier = Modifier.padding(top = 4.dp),
        fontWeight = FontWeight.SemiBold,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center
    )
}