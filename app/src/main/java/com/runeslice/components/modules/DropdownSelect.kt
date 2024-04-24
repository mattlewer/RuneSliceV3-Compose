package com.runeslice.components.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.runeslice.R

@Composable
fun DropdownSelect(options: Array<String>, setSelected: (index: Int) -> Unit, selectedIndex: Int) {
    var opened by remember { mutableStateOf(false) }
    Column {
        Button(
            onClick = { opened = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.watermelon_red),
                contentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.disabled),
                disabledContentColor = Color.Gray,
            ),
            modifier = Modifier
                .width(250.dp)
                .padding(vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = options[selectedIndex],
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 30.dp)
                    )
                }
                Icon(
                    imageVector = if (opened) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = ""
                )
            }

        }
        DropdownMenu(
            expanded = opened,
            modifier = Modifier
                .requiredSizeIn(maxHeight = 350.dp, minWidth = 250.dp)
                .background(color = Color.White),
            onDismissRequest = { opened = false },
            content = {
                for (index in options.indices) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .clickable(onClick = {
                                setSelected(index)
                                opened = false
                            })
                    ) {
                        Text(text = options[index], modifier = Modifier.padding(horizontal = 10.dp))
                    }

                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewDropdownSelect() {
    DropdownSelect(
        options = arrayOf("Test", "Testing", "Tester"),
        setSelected = {},
        selectedIndex = 2
    )
}