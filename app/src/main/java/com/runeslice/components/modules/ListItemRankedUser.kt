package com.runeslice.components.modules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runeslice.R

@Composable
fun ListItemRankedUser(username: String, position: Int, content: @Composable () -> Unit) {

    fun generateImage(position: Int): Int {
        if(position == 0){
            return R.drawable.crown_gold
        }
        if(position == 1){
            return R.drawable.crown_silver
        }
        if(position == 2){
            return R.drawable.crown_bronze
        }
        else{
            return R.drawable.gnome_child
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = generateImage(position)),
            contentDescription = "",
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Surface(
            elevation = 3.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = username,
                    color = colorResource(id = R.color.watermelon_red),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .background(colorResource(id = R.color.watermelon_red)),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    content()
                }
            }
        }
    }
}
