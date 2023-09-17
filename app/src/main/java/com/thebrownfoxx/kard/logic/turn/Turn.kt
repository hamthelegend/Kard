package com.thebrownfoxx.kard.logic.turn

import com.thebrownfoxx.kard.logic.Player
import com.thebrownfoxx.kard.logic.extension.CoinFace

enum class TurnType(val card: Card? = null) {
    Attack,
    DoubleDamageAttack(Card.DoubleDamageAttack),
    Block(Card.Block),
    Heal(Card.Heal);
}

sealed class Turn(val type: TurnType) {
    abstract val player: Player

    data class Attack(
        override val player: Player,
        val doubleDamage: Boolean,
    ) : Turn(if (doubleDamage) TurnType.DoubleDamageAttack else TurnType.Attack)

    data class Block(
        override val player: Player,
        val guessedCoin: CoinFace,
    ) : Turn(TurnType.Block)

    data class Heal(override val player: Player) : Turn(TurnType.Heal)
}