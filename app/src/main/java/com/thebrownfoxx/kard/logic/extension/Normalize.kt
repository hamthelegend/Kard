package com.thebrownfoxx.kard.logic.extension

fun Int.normalize(min: Int, max: Int) = when {
    this < min -> min
    this > max -> max
    else -> this
}