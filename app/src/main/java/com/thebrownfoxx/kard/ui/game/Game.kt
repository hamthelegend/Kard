package com.thebrownfoxx.kard.ui.game

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.ui.extension.Elevation
import com.thebrownfoxx.kard.ui.game.cardselector.CardSelector
import com.thebrownfoxx.kard.ui.game.playerinfo.AiInfo
import com.thebrownfoxx.kard.ui.game.playerinfo.PlayerInfo
import com.thebrownfoxx.kard.ui.theme.KardTheme

@Composable
fun Game(
    player: Player,
    ai: Player,
    uiState: UiState,
    modifier: Modifier = Modifier,
) {
    Surface {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            modifier = modifier.padding(16.dp),
        ) {
            AiInfo(ai = ai)
            PlayerInfo(player = player)
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = Elevation.level(1),
            ) {
                AnimatedContent(targetState = uiState) { newState ->
                    when (newState) {
                        is UiState.SelectingTurn -> CardSelector(
                            cards = player.cards,
                            selectedCard = newState.selectedCard,
                            onCardSelected = { TODO() },
                            onCommitCard = { TODO() },
                            modifier = Modifier.padding(16.dp),
                        )
                        is UiState.Attacking -> TODO()
                        is UiState.Blocking -> TODO()
                        is UiState.ShowingSupposedTurnResult1 -> TODO()
                        is UiState.ShowingSupposedTurnResult2 -> TODO()
                        is UiState.ShowingTurnResult -> TODO()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GamePreview() {
    KardTheme {
        Game(
            player = Player("Player").damagedBy(50),
            ai = Player("AI").damagedBy(70).usedCard(null),
            uiState = UiState.SelectingTurn(selectedCard = null),
        )
    }
}