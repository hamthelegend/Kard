package com.thebrownfoxx.kard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun Shape(
    shape: Shape,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.background(color, shape))
}