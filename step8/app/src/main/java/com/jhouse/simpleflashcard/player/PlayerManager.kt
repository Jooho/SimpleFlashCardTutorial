package com.jhouse.simpleflashcard.player

import android.content.Context
import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerAndConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.svc.PlayerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PlayerManager private constructor(context: Context) {
    private val logger: Logger = LoggerFactory.getLogger(PlayerManager::class.java)

    companion object : SingletonHolder<PlayerManager, Context>(::PlayerManager)

    private var players = mutableListOf<Player>()

    private val playerService = PlayerService.getInstance(context)

    fun clearPlayers(): Unit {
        players.clear()
    }


    fun initPlayerManager() {
        if (players.isNotEmpty()) clearPlayers()

        val playerAndConfigEntities = playerService.getPlayerAndConfigEntities()
        playerAndConfigEntities.forEach {
            players.add(PlayerFactory.createPlayer(it))
        }
    }

    fun getPlayers(): List<Player>{
        return players.toList()
    }

    fun getPlayerByName(name: String): Player {
        //TODO(excpetion handling when player does not exist)
        return players.filter { p
            -> p.name == name
        }[0]
    }

    fun addPlayer(playerName:String){
        val playerAndConfigEntity = playerService.addPlayer(playerName)
        players.add(PlayerFactory.createPlayer(playerAndConfigEntity))
    }

    fun updatePlayer(oldPlayer:Player,newPlayer:Player){
        val updatedPlayer = playerService.updatePlayer(convertPlayerToPlayerAndConfigEntity(newPlayer))

        players[players.indexOf(oldPlayer)] = PlayerFactory.createPlayer(updatedPlayer)
    }

    //Test Purpose
    fun addPlayer(playerAndConfigEntity: PlayerAndConfigEntity){
        players.add(PlayerFactory.createPlayer(playerAndConfigEntity))
    }

    private fun convertPlayerToPlayerAndConfigEntity(player:Player): PlayerAndConfigEntity{
        val config = player.config
        return PlayerAndConfigEntity(PlayerEntity(player.id,player.name),
            ConfigEntity(player.id,config.questionType,config.timeLimitSecs,config.questionCount,config.saveResult)
        )

    }
}