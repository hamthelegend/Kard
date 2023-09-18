package com.thebrownfoxx.kard.ui.game.cardselector

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Card(
    card: Card,
    count: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = when (card) {
        Card.DoubleDamageAttack -> ImageVector.vectorResource(id = R.drawable.axe)
        Card.Block -> KardIcons.Shield
        Card.Heal -> KardIcons.Favorite
    }
    val contentDescription = when (card) {
        Card.DoubleDamageAttack -> stringResource(R.string.double_damage_attack)
        Card.Block -> stringResource(R.string.block)
        Card.Heal -> stringResource(R.string.heal)
    }

    val disabled = count <= 0

    val color by animateColorAsState(
        when {
            disabled -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f)
            selected -> MaterialTheme.colorScheme.tertiary
            else -> MaterialTheme.colorScheme.tertiaryContainer
        },
    )

    val contentColor by animateColorAsState(
        when {
            disabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            selected -> MaterialTheme.colorScheme.onTertiary
            else -> MaterialTheme.colorScheme.onTertiaryContainer
        }
    )

    Surface(
        onClick = onClick,
        enabled = !disabled,
        color = color,
        contentColor = contentColor,
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .height(96.dp)
                .width(64.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.align(Alignment.Center),
            )
            Surface(
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = "×$count",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    KardTheme {
        Card(
            card = Card.Block,
            count = 3,
            selected = false,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
fun SelectedCardPreview() {
    KardTheme {
        Card(
            card = Card.Heal,
            count = 1,
            selected = true,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
fun DisabledCardPreview() {
    KardTheme {
        Card(
            card = Card.Heal,
            count = 0,
            selected = true,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}