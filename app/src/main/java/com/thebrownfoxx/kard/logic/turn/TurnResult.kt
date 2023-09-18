package com.thebrownfoxx.kard.logic.turn

import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.turn.TurnResult.AttackAttack
import com.thebrownfoxx.kard.logic.turn.TurnResult.AttackBlock
import com.thebrownfoxx.kard.logic.turn.TurnResult.AttackHeal
import com.thebrownfoxx.kard.logic.turn.TurnResult.BlockBlock
import com.thebrownfoxx.kard.logic.turn.TurnResult.BlockHeal
import com.thebrownfoxx.kard.logic.turn.TurnResult.HealHeal

sealed class TurnResult {
    abstract val supposedTurnResult1: SupposedTurnResult
    abstract val supposedTurnResult2: SupposedTurnResult

    fun supposedTurnOf(player: Player) = when (player.id) {
        supposedTurnResult1.player.id -> supposedTurnResult1
        supposedTurnResult2.player.id -> supposedTurnResult2
        else -> throw IllegalArgumentException("$player is not a player of this game.")
    }

    /**
     * Whoever has the stronger damage does damage. The weaker damage is canceled.
     * Damage is canceled if the damages are equal.
     * Example: Player won the duel and dealt 25 damage to AI.
     * Example: Opponents of equal strength! No damage was done.
     */
    data class AttackAttack(
        val attack1: SupposedTurnResult.Attack,
        val attack2: SupposedTurnResult.Attack,
    ) : TurnResult() {
        override val supposedTurnResult1 = attack1
        override val supposedTurnResult2 = attack2

        val winner = when {
            attack1.totalDamage > attack2.totalDamage -> attack1.player
            attack1.totalDamage < attack2.totalDamage -> attack2.player
            else -> null
        }
        val loser = when {
            attack1.totalDamage < attack2.totalDamage -> attack1.player
            attack1.totalDamage > attack2.totalDamage -> attack2.player
            else -> null
        }
        val damageDealt = when (winner) {
            attack1.player -> attack1.totalDamage
            attack2.player -> attack2.totalDamage
            else -> 0
        }
    }

    /**
     * Whoever blocks gets the chance to reduce the damage of the attack.
     * Example: Player blocked half the damage and only received 15 damage from AI.
     * Example: A critical block! Player has blocked all the damage from the AI.
     */
    data class AttackBlock(
        val attack: SupposedTurnResult.Attack,
        val block: SupposedTurnResult.Block,
    ) : TurnResult() {
        override val supposedTurnResult1 = attack
        override val supposedTurnResult2 = block

        val damageDealt = block.totalDamage
    }

    /**
     * All healing is canceled.
     * Example: Player's healing failed and received 25 damage from AI.
     */
    data class AttackHeal(
        val attack: SupposedTurnResult.Attack,
        val heal: SupposedTurnResult.Heal,
    ) : TurnResult() {
        override val supposedTurnResult1 = attack
        override val supposedTurnResult2 = heal
    }

    /**
     * Nothing happens.
     * Example: Nothing happened.
     */
    data class BlockBlock(
        val block1: SupposedTurnResult.Block,
        val block2: SupposedTurnResult.Block,
    ) : TurnResult() {
        override val supposedTurnResult1 = block1
        override val supposedTurnResult2 = block2
    }

    /**
     * Healing goes through.
     * Example: Player healed by 50 hp.
     */
    data class BlockHeal(
        val block: SupposedTurnResult.Block,
        val heal: SupposedTurnResult.Heal,
    ) : TurnResult() {
        override val supposedTurnResult1 = block
        override val supposedTurnResult2 = heal
    }

    /**
     * Both get healed.
     * Example: Both players healed by 50 hp.
     */
    data class HealHeal(
        val heal1: SupposedTurnResult.Heal,
        val heal2: SupposedTurnResult.Heal,
    ) : TurnResult() {
        override val supposedTurnResult1 = heal1
        override val supposedTurnResult2 = heal2
    }
}

fun SupposedTurnResults.calculateResult() = when (supposedTurnResult1) {
    is SupposedTurnResult.Attack -> when (supposedTurnResult2) {
        is SupposedTurnResult.Attack -> AttackAttack(supposedTurnResult1, supposedTurnResult2)
        is SupposedTurnResult.Block -> AttackBlock(supposedTurnResult1, supposedTurnResult2)
        is SupposedTurnResult.Heal -> AttackHeal(supposedTurnResult1, supposedTurnResult2)
    }

    is SupposedTurnResult.Block -> when (supposedTurnResult2) {
        is SupposedTurnResult.Block -> BlockBlock(supposedTurnResult1, supposedTurnResult2)
        is SupposedTurnResult.Heal -> BlockHeal(supposedTurnResult1, supposedTurnResult2)
        else -> throw IllegalStateException(
            "SupposedTurnResult.Block cannot go before SupposedTurnResult.Attack"
        )
    }

    is SupposedTurnResult.Heal -> when (supposedTurnResult2) {
        is SupposedTurnResult.Attack -> AttackHeal(supposedTurnResult2, supposedTurnResult1)
        is SupposedTurnResult.Block -> BlockHeal(supposedTurnResult2, supposedTurnResult1)
        is SupposedTurnResult.Heal -> HealHeal(supposedTurnResult1, supposedTurnResult2)
    }
}