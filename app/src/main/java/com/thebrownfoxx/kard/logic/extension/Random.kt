package com.thebrownfoxx.kard.logic.extension

object Coin {
    enum class Face { Heads, Tails }
    fun flip() = Face.values().random()
}

typealias CoinFace = Coin.Face

object Die {
    const val Faces = 6
    fun roll() = (1..Faces).random()
}