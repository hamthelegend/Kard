package com.thebrownfoxx.kard.ui.component.coin

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.logic.extension.CoinFace

@Composable
fun Coin(
    value: CoinFace?,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier,
    ) {
        if (value == null) {
            NullCoinFace(modifier = Modifier.padding(16.dp))
        } else {
            CoinFace(
                value = value,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Coin(
    value: CoinFace?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier,
    ) {
        if (value == null) {
            NullCoinFace(modifier = Modifier.padding(16.dp))
        } else {
            CoinFace(
                value = value,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}