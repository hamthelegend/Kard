package com.thebrownfoxx.kard.logic

import com.thebrownfoxx.kard.logic.extension.Coin
import com.thebrownfoxx.kard.logic.turn.Turn
import com.thebrownfoxx.kard.logic.turn.TurnResult
import com.thebrownfoxx.kard.logic.turn.TurnType
import com.thebrownfoxx.kard.logic.turn.calculateResult
import com.thebrownfoxx.kard.logic.turn.calculateSupposedResults
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class Game(playerName: String, aiName: String) {
    private val _player = MutableStateFlow(Player(playerName))
    val player: StateFlow<Player> = _player

    private val _ai = MutableStateFlow(Player(aiName))
    val ai: StateFlow<Player> = _ai

    private val _turnResult = MutableStateFlow<TurnResult?>(null)
    val turnResult: StateFlow<TurnResult?> = _turnResult

    val winner = player.combine(ai) { player, ai ->
        when {
            player.dead -> ai
            ai.dead -> player
            else -> null
        }
    }

    val over = winner.map { it != null }

    private val Player.state get() = when (this) {
        player.value -> _player
        else -> _ai
    }

    private val Player.enemyState get() = when (this) {
        player.value -> _ai
        else -> _player
    }

    fun play(playerTurn: Turn) {
        if (playerTurn.type !in player.value.availableTurnTypes)
            throw IllegalArgumentException("Player cannot play that card")

        val aiTurnType = ai.value.availableTurnTypes.random()
        if (aiTurnType !in ai.value.availableTurnTypes)
            throw IllegalArgumentException("AI cannot play that card")

        _player.update { it.usedCard(playerTurn.type.card) }
        _ai.update { it.usedCard(aiTurnType.card) }

        val aiTurn = when (aiTurnType) {
            TurnType.Attack -> Turn.Attack(player = ai.value, doubleDamage = false)
            TurnType.DoubleDamageAttack -> Turn.Attack(player = ai.value, doubleDamage = true)
            TurnType.Block -> Turn.Block(player = ai.value, guessedCoin = Coin.flip())
            TurnType.Heal -> Turn.Heal(player = ai.value)
        }

        _turnResult.update {
            calculateSupposedResults(playerTurn, aiTurn).calculateResult()
        }
    }

    fun commitResult() {
        turnResult.value?.run {
            when (this) {
                is TurnResult.AttackAttack -> {
                    loser?.state?.update {
                        loser.damagedBy(damageDealt)
                    }
                }
                is TurnResult.AttackBlock -> {
                    block.player.state.update {
                        block.player.damagedBy(damageDealt)
                    }
                }
                is TurnResult.AttackHeal -> {
                    heal.player.state.update {
                        heal.player.damagedBy(attack.totalDamage)
                    }
                }
                is TurnResult.BlockBlock -> {}
                is TurnResult.BlockHeal -> {
                    heal.player.state.update {
                        heal.player.healedBy(Player.HealAmount)
                    }
                }
                is TurnResult.HealHeal -> {
                    for (heal in listOf(heal1, heal2)) {
                        heal.player.state.update {
                            heal.player.healedBy(Player.HealAmount)
                        }
                    }
                }
            }
        }
        _turnResult.update { null }
    }
}