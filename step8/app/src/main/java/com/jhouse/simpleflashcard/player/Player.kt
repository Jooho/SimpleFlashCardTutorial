package com.jhouse.simpleflashcard.player

import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class Player(val id:Int, val name:String, val config: Config) {
    private val logger: Logger = LoggerFactory.getLogger(Player::class.java)

}