package com.thebrownfoxx.kard.ui.game.cardselector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.ui.component.ActionCard
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun CardSelector(
    cards: List<Card>,
    selectedCard: Card?,
    onCardSelected: (Card?) -> Unit,
    onCardCommitted: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val title = stringResource(
        when (selectedCard) {
            null -> R.string.draw_and_attack
            Card.DoubleDamageAttack -> R.string.double_damage_attack
            Card.Block -> R.string.block
            Card.Heal -> R.string.heal
        }
    )

    ActionCard(
        title = title,
        actionButtonIcon = KardIcons.PlayArrow,
        actionButtonText = stringResource(id = R.string.play),
        onActionButtonClick = onCardCommitted,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            NoCardSelector(selected = selectedCard == null, onClick = { onCardSelected(null) })
            for (card in Card.values()) {
                Card(
                    card = card,
                    count = cards.count { it == card },
                    selected = card == selectedCard,
                    onClick = { onCardSelected(card) },
                )
            }
        }
    }
}

@Preview
@Composable
fun CardSelectorPreview() {
    val cards =
        remember { Array(5) { Card.draw() }.toList().filter { it != Card.DoubleDamageAttack } }
    var selectedCard by remember { mutableStateOf<Card?>(null) }

    KardTheme {
        CardSelector(
            cards = cards,
            selectedCard = selectedCard,
            onCardSelected = { selectedCard = it },
            onCardCommitted = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}