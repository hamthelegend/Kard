package com.thebrownfoxx.kard.logic

class PlayerNotFoundException(player: Player) :
    IllegalArgumentException("$player is not a player of this game.")