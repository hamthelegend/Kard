package com.thebrownfoxx.kard.ui.game

import com.thebrownfoxx.kard.logic.extension.CoinFace
import com.thebrownfoxx.kard.logic.turn.Card
import com.thebrownfoxx.kard.logic.turn.SupposedTurnResult
import com.thebrownfoxx.kard.logic.turn.TurnResult

sealed class UiState {
    data class SelectingTurn(val selectedCard: Card?) : UiState()
    data class Attacking(val rolledDie: Int?) : UiState()
    data class Blocking(val guessedCoin: CoinFace?, val flippedCoin: CoinFace?) : UiState()
    data class ShowingSupposedTurnResult1(val supposedTurnResult: SupposedTurnResult) : UiState()
    data class ShowingSupposedTurnResult2(val supposedTurnResult: SupposedTurnResult) : UiState()
    data class ShowingTurnResult(val turnResult: TurnResult) : UiState()
}