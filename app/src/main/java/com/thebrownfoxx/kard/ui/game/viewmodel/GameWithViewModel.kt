package com.thebrownfoxx.kard.ui.game.viewmodel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thebrownfoxx.kard.ui.game.Game
import com.thebrownfoxx.kard.ui.game.UiState

@Composable
fun GameWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel(),
) {
    val player by viewModel.game.player.collectAsState()
    val ai by viewModel.game.ai.collectAsState()
    val gameOver by viewModel.game.over.collectAsState(initial = false)
    val showGameOver = gameOver && viewModel.uiState is UiState.SelectingTurn
    val winner by viewModel.game.winner.collectAsState(initial = null)
    val playerWon = player.id == winner?.id

    Game(
        player = player,
        ai = ai,
        uiState = viewModel.uiState,
        onCardSelected = viewModel::onCardSelected,
        onCardCommitted = viewModel::onCardCommitted,
        onCoinGuessed = viewModel::onCoinGuessed,
        onTurnAcknowledged = viewModel::onTurnAcknowledged,
        onSupposedTurnResult1Acknowledged = viewModel::onSupposedTurnResult1Acknowledged,
        onSupposedTurnResult2Acknowledged = viewModel::onSupposedTurnResult2Acknowledged,
        onTurnResultAcknowledged = viewModel::onTurnResultAcknowledged,
        showGameOver = showGameOver,
        playerWon = playerWon,
        onGameOverAcknowledge = viewModel::onGameOverAcknowledge,
        modifier = modifier.fillMaxSize(),
    )
}