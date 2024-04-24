package com.runeslice.components.modules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.runeslice.R

@Composable
fun WelcomeTextAndLogo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.runeslice_logo),
            contentDescription = null,
        )
        Text(
            text = "RuneSlice", color = colorResource(id = R.color.watermelon_red),
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(text = "A slice of your OSRS life", fontSize = 14.sp)
    }
}