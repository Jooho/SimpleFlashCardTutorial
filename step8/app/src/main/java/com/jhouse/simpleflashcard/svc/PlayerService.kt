package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerAndConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.player.Config
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerService  private constructor(context: Context){

    private val logger: Logger = LoggerFactory.getLogger(PlayerService::class.java)

    companion object : SingletonHolder<PlayerService, Context>(::PlayerService)

    private var db: FlashCardDB = FlashCardDB.getInstance(context)
    private val configService= ConfigService.getInstance(context)

    fun addPlayer(playerName: String) : PlayerAndConfigEntity{
        val defaultConfig = Config()
        val lastPlayerId = db.playerDao().getPlayers().size
        db.playerDao().insertPlayer(PlayerEntity(lastPlayerId+1,playerName))
        configService.addConfig(ConfigEntity(lastPlayerId+1, defaultConfig.questionType,defaultConfig.timeLimitSecs,defaultConfig.questionCount,defaultConfig.saveResult))

        return db.playerDao().getPlayerByName(playerName)
    }

    fun addPlayer(player: PlayerEntity) {
        val defaultConfig = Config()
        db.playerDao().insertPlayer(PlayerEntity(player.id,player.name))
        configService.addConfig(ConfigEntity(player.id, defaultConfig.questionType,defaultConfig.timeLimitSecs,defaultConfig.questionCount,defaultConfig.saveResult))
    }

    fun updatePlayer(playerAndConfigEntity: PlayerAndConfigEntity): PlayerAndConfigEntity {
        val player = playerAndConfigEntity.player
        val config = playerAndConfigEntity.config
        db.playerDao().updatePlayer(PlayerEntity(player.id,player.name))
        configService.updatConfig(ConfigEntity(player.id, config.questionType,config.timeLimitSecs,config.questionCount,config.saveResult))

        return db.playerDao().getPlayerByName(player.name)
    }

    fun getPlayerAndConfigEntities(): List<PlayerAndConfigEntity> {
       return db.playerDao().getPlayers()

    }

}
