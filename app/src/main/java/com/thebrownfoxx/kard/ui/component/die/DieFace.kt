package com.thebrownfoxx.kard.ui.component.die

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.ui.component.Shape
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun BoxScope.DieDot(
    alignment: Alignment,
    modifier: Modifier = Modifier,
) {
    Shape(
        shape = CircleShape,
        color = LocalContentColor.current,
        modifier = modifier
            .size(16.dp)
            .align(alignment),
    )
}

@Composable
fun DieFace(
    value: Int?,
    modifier: Modifier = Modifier,
) {
    when (value) {
        null -> NullDieFace(modifier)
        1 -> DieFace1(modifier)
        2 -> DieFace2(modifier)
        3 -> DieFace3(modifier)
        4 -> DieFace4(modifier)
        5 -> DieFace5(modifier)
        6 -> DieFace6(modifier)
        else -> NonStandardDieFace(value = value, modifier = modifier)
    }
}

@Composable
fun NullDieFace(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        Text(
            text = "?",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun DieFace1(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        DieDot(alignment = Alignment.Center)
    }
}

@Composable
fun DieFace2(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        DieDot(alignment = Alignment.TopEnd)
        DieDot(alignment = Alignment.BottomStart)
    }
}

@Composable
fun DieFace3(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        DieDot(alignment = Alignment.TopEnd)
        DieDot(alignment = Alignment.Center)
        DieDot(alignment = Alignment.BottomStart)
    }
}

@Composable
fun DieFace4(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        DieDot(alignment = Alignment.TopStart)
        DieDot(alignment = Alignment.BottomStart)
        DieDot(alignment = Alignment.TopEnd)
        DieDot(alignment = Alignment.BottomEnd)
    }
}

@Composable
fun DieFace5(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        DieDot(alignment = Alignment.TopStart)
        DieDot(alignment = Alignment.BottomStart)
        DieDot(alignment = Alignment.Center)
        DieDot(alignment = Alignment.TopEnd)
        DieDot(alignment = Alignment.BottomEnd)
    }
}

@Composable
fun DieFace6(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp)) {
        DieDot(alignment = Alignment.TopStart)
        DieDot(alignment = Alignment.CenterStart)
        DieDot(alignment = Alignment.BottomStart)
        DieDot(alignment = Alignment.TopEnd)
        DieDot(alignment = Alignment.CenterEnd)
        DieDot(alignment = Alignment.BottomEnd)
    }
}

@Composable
fun NonStandardDieFace(
    value: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.size(64.dp)) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview
@Composable
fun NullDieFacePreview() {
    KardTheme {
        NullDieFace(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DieFace1Preview() {
    KardTheme {
        DieFace1(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DieFace2Preview() {
    KardTheme {
        DieFace2(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DieFace3Preview() {
    KardTheme {
        DieFace3(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DieFace4Preview() {
    KardTheme {
        DieFace4(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DieFace5Preview() {
    KardTheme {
        DieFace5(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun DieFace6Preview() {
    KardTheme {
        DieFace6(modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun NonStandardDieFacePreview() {
    KardTheme {
        NonStandardDieFace(value = 7, modifier = Modifier.padding(16.dp))
    }
}