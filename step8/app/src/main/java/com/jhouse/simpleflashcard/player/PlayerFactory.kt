package com.jhouse.simpleflashcard.player

import com.jhouse.simpleflashcard.db.player.PlayerAndConfigEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object PlayerFactory {
    private val logger: Logger = LoggerFactory.getLogger(PlayerFactory::class.java)

    fun createPlayer(playerAndConfigEntity: PlayerAndConfigEntity): Player {
        val configEntity = playerAndConfigEntity.config
        val playerEntity = playerAndConfigEntity.player
        var config = Config(
            configEntity.questionType,
            configEntity.timeLimitSecs,
            configEntity.questionCount,
            configEntity.saveResult
        )

        return Player(
            playerEntity.id, playerEntity.name, config
        )

    }


}