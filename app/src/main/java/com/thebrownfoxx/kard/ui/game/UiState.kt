package com.thebrownfoxx.kard.ui.game

import com.thebrownfoxx.kard.logic.extension.CoinFace
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.logic.turn.SupposedTurnResult
import com.thebrownfoxx.kard.logic.turn.TurnResult

sealed class UiState {
    data class SelectingTurn(val selectedCard: Card?) : UiState()
    data class Attacked(val rolledDie: Int, val doubleDamage: Boolean) : UiState()
    object BlockingGuessing : UiState()
    data class BlockingFlipped(val guessedCoin: CoinFace, val flippedCoin: CoinFace) : UiState()
    data class ShowingSupposedTurnResult1(val supposedTurnResult: SupposedTurnResult) : UiState()
    data class ShowingSupposedTurnResult2(val supposedTurnResult: SupposedTurnResult) : UiState()
    data class ShowingTurnResult(val turnResult: TurnResult) : UiState()
}