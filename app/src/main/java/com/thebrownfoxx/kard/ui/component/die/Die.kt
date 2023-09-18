package com.thebrownfoxx.kard.ui.component.die

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.logic.extension.Die
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun Die(
    value: Int?,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = value,
            transitionSpec = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) togetherWith
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up)
            }
        ) { newValue ->
            DieContent(value = newValue, modifier = Modifier.padding(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Die(
    onClick: () -> Unit,
    value: Int?,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = value,
            transitionSpec = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) togetherWith
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up)
            }
        ) { newValue ->
            DieContent(value = newValue, modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview
@Composable
fun DiePreview() {
    var value by remember { mutableStateOf<Int?>(null) }
    KardTheme {
        Die(
            value = value,
            onClick = { value = if (value == null) Die.roll() else null },
            modifier = Modifier.padding(16.dp),
        )
    }
}