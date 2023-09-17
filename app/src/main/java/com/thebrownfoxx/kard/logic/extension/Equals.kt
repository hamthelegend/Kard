package com.thebrownfoxx.kard.logic.extension

fun <T> T.equalsALl(vararg others: T) = others.all { it == this }

fun <T> T.equalsOne(vararg others: T) = others.any { it == this }