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
    viewModel.apply {
        val player by game.player.collectAsState()
        val ai by game.ai.collectAsState()
        val gameOver by game.over.collectAsState(initial = false)
        val showGameOver = gameOver && uiState is UiState.SelectingTurn
        val winner by game.winner.collectAsState(initial = null)
        val playerWon = player.id == winner?.id

        Game(
            player = player,
            ai = ai,
            uiState = uiState,
            onCardSelect = ::onCardSelect,
            onCardCommit = ::onCardCommit,
            onCoinGuess = ::onCoinGuess,
            onTurnAcknowledge = ::onTurnAcknowledge,
            onSupposedTurnResult1Acknowledge = ::onSupposedTurnResult1Acknowledge,
            onSupposedTurnResult2Acknowledge = ::onSupposedTurnResult2Acknowledge,
            onTurnResultAcknowledge = ::onTurnResultAcknowledge,
            showGameOver = showGameOver,
            playerWon = playerWon,
            onGameOverAcknowledge = ::onGameOverAcknowledge,
            modifier = modifier.fillMaxSize(),
        )
    }
}