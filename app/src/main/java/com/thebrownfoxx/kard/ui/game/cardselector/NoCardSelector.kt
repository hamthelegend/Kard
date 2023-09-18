package com.thebrownfoxx.kard.ui.game.cardselector

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.ui.theme.KardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoCardSelector(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color by animateColorAsState(
        when {
            selected -> MaterialTheme.colorScheme.tertiary
            else -> MaterialTheme.colorScheme.tertiaryContainer
        },
    )

    val contentColor by animateColorAsState(
        when {
            selected -> MaterialTheme.colorScheme.onTertiary
            else -> MaterialTheme.colorScheme.onTertiaryContainer
        }
    )

    Surface(
        onClick = onClick,
        color = color,
        contentColor = contentColor,
        shape = CircleShape,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.size(64.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.sword),
                contentDescription = stringResource(R.string.no_card),
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
fun NoCardTurnSelectorPreview() {
    KardTheme {
        NoCardSelector(
            selected = false,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
fun SelectedNoCardTurnSelectorPreview() {
    KardTheme {
        NoCardSelector(
            selected = true,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}