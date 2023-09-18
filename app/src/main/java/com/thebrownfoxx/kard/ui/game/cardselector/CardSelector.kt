package com.thebrownfoxx.kard.ui.game.cardselector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Shield
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.R
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.ui.component.SimpleButton
import com.thebrownfoxx.kard.ui.theme.KardIcons
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun CardSelector(
    cards: List<Card>,
    selectedCard: Card?,
    onCardSelected: (Card?) -> Unit,
    onCommitCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedCardIcon = when (selectedCard) {
        null -> ImageVector.vectorResource(id = R.drawable.sword)
        Card.DoubleDamageAttack -> ImageVector.vectorResource(id = R.drawable.axe)
        Card.Block -> KardIcons.Shield
        Card.Heal -> KardIcons.Favorite
    }

    val selectedCardName = when (selectedCard) {
        null -> stringResource(R.string.draw_and_attack)
        Card.DoubleDamageAttack -> stringResource(R.string.double_damage_attack)
        Card.Block -> stringResource(R.string.block)
        Card.Heal -> stringResource(R.string.heal)
    }

    Column(modifier = modifier) {
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
        Spacer(modifier = Modifier.height(16.dp))
        SimpleButton(
            icon = selectedCardIcon,
            text = selectedCardName,
            onClick = onCommitCard,
        )
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
            onCommitCard = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}