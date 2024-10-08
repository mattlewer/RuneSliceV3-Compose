package com.runeslice.components.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.runeslice.models.User

@Composable
fun ListItemSavedUser(user: User, onClick: (user: User) -> Unit) {
    Surface(
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(start = 10.dp, end = 5.dp)
        ) {
            Text(
                text = user.name
            )
            Box(Modifier.width(100.dp)) {
                PrimaryButton(
                    type = ButtonType.PRIMARY,
                    onClick = {onClick(user)},
                    buttonText = "Search"
                )
            }
        }
    }
}