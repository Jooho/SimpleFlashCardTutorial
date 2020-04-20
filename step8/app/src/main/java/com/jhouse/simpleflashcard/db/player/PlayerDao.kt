package com.jhouse.simpleflashcard.db.player

import androidx.room.*

@Dao
interface PlayerDao {

    @Insert
    fun insertPlayer(player: PlayerEntity)

    @Transaction
    @Query("Select * from Player")
    fun getPlayers(): List<PlayerAndConfigEntity>

    @Transaction
    @Query("Select * from Player where Player.name = :playerName")
    fun getPlayerByName(playerName: String): PlayerAndConfigEntity

    @Update
    fun updatePlayer(player: PlayerEntity)

    @Delete
    fun deletePlayer(player: PlayerEntity): Unit



}