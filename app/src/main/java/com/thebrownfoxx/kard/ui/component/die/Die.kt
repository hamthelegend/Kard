package com.thebrownfoxx.kard.ui.component.die

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun Die(
    value: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier,
    ) {
        DieFace(value = value, modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DiePreview() {
    KardTheme {
        Die(
            value = 5,
            modifier = Modifier.padding(16.dp),
        )
    }
}