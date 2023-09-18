package com.thebrownfoxx.kard.ui.game.viewmodel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thebrownfoxx.kard.ui.game.Game

@Composable
fun GameWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel(),
) {
    val player by viewModel.game.player.collectAsState()
    val ai by viewModel.game.ai.collectAsState()

    Game(
        player = player,
        ai = ai,
        uiState = viewModel.uiState,
        onCardSelected = viewModel::onCardSelected,
        onCardCommitted = viewModel::onCardCommitted,
        onCoinGuessed = viewModel::onCoinGuessed,
        onTurnAcknowledged = viewModel::onTurnAcknowledged,
        modifier = modifier.fillMaxSize(),
    )
}