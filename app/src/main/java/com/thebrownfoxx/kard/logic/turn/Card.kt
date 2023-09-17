package com.thebrownfoxx.kard.logic.turn

enum class Card {
    DoubleDamageAttack,
    Block,
    Heal;

    companion object {
        fun draw() = values().random()
    }
}