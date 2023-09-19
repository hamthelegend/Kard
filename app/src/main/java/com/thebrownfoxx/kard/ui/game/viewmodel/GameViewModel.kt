package com.thebrownfoxx.kard.ui.game.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.thebrownfoxx.kard.logic.Game
import com.thebrownfoxx.kard.logic.extension.CoinFace
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.logic.turn.SupposedTurnResult
import com.thebrownfoxx.kard.ui.game.UiState

val newGame get() = Game(playerName = "Player", aiName = "AI")

class GameViewModel : ViewModel() {
    var game by mutableStateOf(newGame)
        private set

    var uiState by mutableStateOf<UiState>(UiState.SelectingTurn(selectedCard = null))
        private set

    fun onCardSelected(card: Card?) {
        uiState = UiState.SelectingTurn(card)
    }

    fun onCardCommitted() {
        val currentUiState = uiState
        if (currentUiState is UiState.SelectingTurn) {
            val player = game.player.value
            when (currentUiState.selectedCard) {
                null -> {
                    val doubleDamage = false
                    game.attack(doubleDamage)
                    val supposedTurnResult = game.turnResult.value?.supposedTurnOf(player)
                    if (supposedTurnResult is SupposedTurnResult.Attack) {
                        uiState = UiState.Attacked(supposedTurnResult.rolledDie, doubleDamage)
                    }
                }

                Card.DoubleDamageAttack -> {
                    val doubleDamage = true
                    game.attack(doubleDamage)
                    val supposedTurnResult = game.turnResult.value?.supposedTurnOf(player)
                    if (supposedTurnResult is SupposedTurnResult.Attack) {
                        uiState = UiState.Attacked(supposedTurnResult.rolledDie, doubleDamage)
                    }
                }

                Card.Block -> uiState = UiState.BlockingGuessing

                Card.Heal -> {
                    game.heal()
                    onTurnAcknowledged()
                }
            }
        }
    }

    fun onCoinGuessed(guessedCoin: CoinFace) {
        val currentUiState = uiState
        if (currentUiState is UiState.BlockingGuessing) {
            game.block(guessedCoin)
            val supposedTurnResult = game.turnResult.value?.supposedTurnOf(game.player.value)
            if (supposedTurnResult is SupposedTurnResult.Block) {
                uiState = UiState.BlockingFlipped(guessedCoin, supposedTurnResult.flippedCoin)
            }
        }
    }

    fun onTurnAcknowledged() {
        val supposedTurnResult = game.turnResult.value?.supposedTurnResult1
        if (supposedTurnResult != null) {
            uiState = UiState.ShowingSupposedTurnResult1(supposedTurnResult)
        }
    }

    fun onSupposedTurnResult1Acknowledged() {
        val supposedTurnResult = game.turnResult.value?.supposedTurnResult2
        if (supposedTurnResult != null) {
            uiState = UiState.ShowingSupposedTurnResult2(supposedTurnResult)
        }
    }

    fun onSupposedTurnResult2Acknowledged() {
        val turnResult = game.turnResult.value
        if (turnResult != null) {
            uiState = UiState.ShowingTurnResult(turnResult)
            game.commitResult()
        }
    }

    fun onTurnResultAcknowledged() {
        uiState = UiState.SelectingTurn(selectedCard = null)
    }

    fun onGameOverAcknowledge() {
        game = newGame
    }
}