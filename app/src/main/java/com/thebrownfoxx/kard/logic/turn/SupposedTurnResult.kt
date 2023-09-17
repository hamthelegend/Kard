package com.thebrownfoxx.kard.logic.turn

import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.extension.Coin
import com.thebrownfoxx.kard.logic.extension.CoinFace
import com.thebrownfoxx.kard.logic.extension.Die
import kotlin.random.Random

data class SupposedTurnResults(
    val supposedTurnResult1: SupposedTurnResult,
    val supposedTurnResult2: SupposedTurnResult,
)

sealed class SupposedTurnResult {
    abstract val player: Player

    data class Attack(
        override val player: Player,
        val rolledDie: Int,
        val doubleDamage: Boolean,
        val totalDamage: Int,
    ) : SupposedTurnResult()

    data class Block(
        override val player: Player,
        val guessedCoin: CoinFace,
        val flippedCoin: CoinFace,
        val criticalBlock: Boolean,
        val originalDamage: Int,
        val totalDamage: Int,
    ) : SupposedTurnResult() {
        val coinsMatch = guessedCoin == flippedCoin
        val blockedAnything = originalDamage > 0
    }

    data class Heal(override val player: Player) : SupposedTurnResult()

    infix fun with(other: SupposedTurnResult) = SupposedTurnResults(this, other)
}

fun calculateSupposedResults(
    unorderedTurn1: Turn,
    unorderedTurn2: Turn,
): SupposedTurnResults {
    val turn1 = if (unorderedTurn1 is Turn.Block && unorderedTurn2 is Turn.Attack) unorderedTurn2 else unorderedTurn1
    val turn2 = if (turn1 == unorderedTurn2) unorderedTurn1 else unorderedTurn2

    return when (turn1) {
        is Turn.Attack -> {
            val attack = turn1.player.calculateAttack()
            attack with when (turn2) {
                is Turn.Attack -> turn2.player.calculateAttack()
                is Turn.Block -> turn2.player.calculateBlock(turn2.guessedCoin, attack.totalDamage)
                is Turn.Heal -> turn2.player.calculateHeal()
            }
        }
        is Turn.Block -> {
            val block = turn1.player.calculateBlock(turn1.guessedCoin, queuedDamage = 0)
            block with when (turn2) {
                is Turn.Block -> turn2.player.calculateBlock(turn2.guessedCoin, 0)
                is Turn.Heal ->  turn2.player.calculateHeal()
                else -> throw IllegalStateException("Turn.Block cannot go before Turn.Attack")
            }
        }
        is Turn.Heal -> {
            val heal = turn1.player.calculateHeal()
            heal with when (turn2) {
                is Turn.Attack -> turn2.player.calculateAttack()
                is Turn.Block -> turn2.player.calculateBlock(turn2.guessedCoin, 0)
                is Turn.Heal -> turn2.player.calculateHeal()
            }
        }
    }
}

fun Player.calculateAttack(doubleDamage: Boolean = false): SupposedTurnResult.Attack {
    val doubleDamageMultiplier = if (doubleDamage) 2 else 1
    val rolledDie = Die.roll()
    val dieMultiplier = rolledDie.toFloat() / Die.Faces
    val totalDamage = (Player.MaxDamage * doubleDamageMultiplier * dieMultiplier).toInt()
    return SupposedTurnResult.Attack(
        player = this,
        rolledDie = rolledDie,
        doubleDamage = doubleDamage,
        totalDamage = totalDamage,
    )
}

fun Player.calculateBlock(guessedCoin: CoinFace, queuedDamage: Int): SupposedTurnResult.Block {
    val flippedCoin = Coin.flip()
    val coinMultiplier = if (flippedCoin == guessedCoin) 0.5f else 1f
    val criticalBlock = cards.isEmpty() && Random.nextBoolean()
    val criticalBlockMultiplier = if (criticalBlock) 0 else 1
    val totalDamage = (queuedDamage * coinMultiplier * criticalBlockMultiplier).toInt()
    return SupposedTurnResult.Block(
        player = this,
        guessedCoin = guessedCoin,
        flippedCoin = flippedCoin,
        criticalBlock = criticalBlock,
        originalDamage = queuedDamage,
        totalDamage = totalDamage,
    )
}

fun Player.calculateHeal() = SupposedTurnResult.Heal(player = this)