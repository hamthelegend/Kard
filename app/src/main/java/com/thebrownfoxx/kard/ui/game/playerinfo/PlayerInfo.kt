package com.thebrownfoxx.kard.ui.game.playerinfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Memory
import androidx.compose.material.icons.twotone.Style
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.ui.component.extension.Shape
import com.thebrownfoxx.kard.ui.extension.Elevation
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun PlayerInfo(
    player: Player,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .clip(MaterialTheme.shapes.small),
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = Elevation.level(1),
            shape = MaterialTheme.shapes.extraSmall,
        ) {
            Box(modifier = Modifier.aspectRatio(1f)) {
                Icon(
                    imageVector = KardIcons.AccountCircle,
                    contentDescription = stringResource(R.string.player),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(32.dp),
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = Elevation.level(1),
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Box(contentAlignment = Alignment.CenterStart) {
                    Shape(
                        shape = RectangleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(player.hp.toFloat() / Player.MaxHp),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Icon(
                            imageVector = KardIcons.Favorite,
                            contentDescription = stringResource(R.string.hp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = player.hp.toString())
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = player.cards.size.toString())
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = KardIcons.Style,
                            contentDescription = stringResource(R.string.cards),
                            modifier = Modifier.rotate(180f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AiInfo(
    ai: Player,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .clip(MaterialTheme.shapes.small),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = Elevation.level(1),
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Box(contentAlignment = Alignment.CenterEnd) {
                    Shape(
                        shape = RectangleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(ai.hp.toFloat() / Player.MaxHp),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Icon(
                            imageVector = KardIcons.Style,
                            contentDescription = stringResource(R.string.cards),
                            modifier = Modifier.rotate(180f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = ai.cards.size.toString())
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = ai.hp.toString())
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = KardIcons.Favorite,
                            contentDescription = stringResource(R.string.hp),
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = Elevation.level(1),
            shape = MaterialTheme.shapes.extraSmall,
        ) {
            Box(modifier = Modifier.aspectRatio(1f)) {
                Icon(
                    imageVector = KardIcons.Memory,
                    contentDescription = stringResource(R.string.player),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(32.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun PlayerInfoPreview() {
    KardTheme {
        PlayerInfo(
            player = Player("Player").damagedBy(30),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
fun AiInfoPreview() {
    KardTheme {
        AiInfo(
            ai = Player("AI").damagedBy(50),
            modifier = Modifier.padding(16.dp),
        )
    }
}