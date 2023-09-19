package com.thebrownfoxx.kard.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.ui.game.cardselector.Card
import com.thebrownfoxx.kard.ui.game.cardselector.NoCardSelector
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun ActionCardContent(
    title: String,
    actionButtonIcon: ImageVector,
    actionButtonText: String,
    onActionButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        AnimatedContent(targetState = title) { title ->
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        Box {
            content()
        }
        SimpleButton(
            icon = actionButtonIcon,
            text = actionButtonText,
            onClick = onActionButtonClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun ActionCardPreview() {
    KardTheme {
        ActionCardContent(
            title = "Attack and Draw",
            actionButtonIcon = KardIcons.PlayArrow,
            actionButtonText = "Play",
            onActionButtonClick = {},
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                NoCardSelector(selected = true, onClick = {})
                for (card in Card.values()) {
                    Card(
                        card = card,
                        count = 3,
                        selected = false,
                        onClick = {},
                    )
                }
            }
        }
    }
}