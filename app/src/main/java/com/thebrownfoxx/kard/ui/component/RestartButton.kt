package com.thebrownfoxx.kard.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.twotone.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun RestartButton(
    onLongClick: (() -> Unit),
    modifier: Modifier = Modifier,
) {
    val color = MaterialTheme.colorScheme.primaryContainer
    val contentColor = contentColorFor(color)
    val progressBarColor = contentColor.copy(alpha = 0.2f)
    val durationBeforeTriggering = 500.milliseconds
    val hapticFeedback = LocalHapticFeedback.current
    var durationHeld by remember { mutableStateOf(Duration.ZERO) }
    var triggered by remember{ mutableStateOf(false) }

    val holdProgressAnimatable = remember { Animatable(0f) }
    LaunchedEffect(durationHeld) {
        if (durationHeld == Duration.ZERO) {
            holdProgressAnimatable.animateTo(targetValue = 0f)
        } else {
            holdProgressAnimatable.snapTo((durationHeld / durationBeforeTriggering).toFloat())
        }
    }
    val holdProgress = holdProgressAnimatable.value

    Surface(
        modifier = modifier,
        color = color,
        contentColor = contentColor,
        shape = CircleShape,
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .drawBehind {
                    drawCircle(
                        color = progressBarColor,
                        radius = size.width / 2 * holdProgress,
                    )
                }
                .onTouchHeld(
                    onRelease = {
                        durationHeld = Duration.ZERO
                        triggered = false
                    },
                ) {
                    if (!triggered) {
                        durationHeld = it
                        if (durationHeld >= durationBeforeTriggering) {
                            onLongClick()
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            triggered = true
                        }
                    }
                },
        ) {
            Box(modifier = modifier.size(64.dp)) {
                Icon(
                    imageVector = KardIcons.RestartAlt,
                    contentDescription = stringResource(R.string.restart),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun RestartPreview() {
    KardTheme {
        RestartButton(onLongClick = { /*TODO*/ }, modifier = Modifier.padding(16.dp))
    }
}