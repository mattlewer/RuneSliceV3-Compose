package com.runeslice.components.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runeslice.R

@Composable
fun TextInputField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
) {
    Box(Modifier.height(45.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 5.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            singleLine = true,
            cursorBrush = SolidColor(colorResource(id = R.color.watermelon_red)),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp, color = colorResource(
                    id = R.color.watermelon_red
                ),
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .align(Alignment.BottomCenter)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = colorResource(id = R.color.watermelon_red))
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun PreviewTextInputField() {
    var textValue by remember { mutableStateOf(String()) }
    TextInputField(
        value = textValue,
        label = "Enter text",
        onValueChange = { textValue = it }
    )
}
