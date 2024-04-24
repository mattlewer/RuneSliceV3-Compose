package com.runeslice.components.modules

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.runeslice.R

enum class ButtonType {
    PRIMARY, SECONDARY
}

@Composable
fun PrimaryButton(
    type: ButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String,
) {
    var containerColor =
        if (type == ButtonType.PRIMARY) R.color.watermelon_red
        else R.color.dark_blue


    Button(
        content = {
            Text(
                text = buttonText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        },
        onClick = onClick, modifier = modifier
            .then(modifier)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = containerColor),
            contentColor = Color.White,
            disabledContainerColor = colorResource(id = R.color.disabled),
            disabledContentColor = Color.Gray,
        )
    )
}

@Preview
@Composable
fun PreviewCustomButton() {
    PrimaryButton(
        type = ButtonType.PRIMARY,
        onClick = { /* Your onClick action here */ },
        buttonText = "Click Me",
    )
}
