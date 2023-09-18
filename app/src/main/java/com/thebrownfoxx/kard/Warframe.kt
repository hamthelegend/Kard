package com.thebrownfoxx.kard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.ui.theme.KardTheme

class Tenno(val hp: Int)

enum class Warframe(val hp: Int) {
    Excal(hp = 100),
    Mag(hp = 150),
    Volt(hp = 200),
}

@Composable
fun WarframeComposable() {
    var selectedWarframe by remember { mutableStateOf(Warframe.Excal) }
    var tenno: Tenno? by remember { mutableStateOf(null) }

    Column {
        Text(text = "Choose your warframe: ")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            for (warframe in Warframe.values()) {
                Button(
                    onClick = {
                        selectedWarframe = warframe
                        tenno = Tenno(hp = warframe.hp)
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = warframe.toString())
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = selectedWarframe.toString())
    }
}

@Preview
@Composable
fun WarframePreview() {
    KardTheme {
        WarframeComposable()
    }
}