package com.jhouse.simpleflashcard.db.result

import androidx.room.*

@Dao
interface PlayerResultDao {

    @Transaction
    @Query("Select * from PlayerResult")
    fun getResultGroups(): List<ResultGroupEntity>

    @Transaction
    @Query("Select * from PlayerResult where player_id =:playerId")
    fun getResultGroupByPlayerId(playerId: Int):ResultGroupEntity

    @Query("Select * from PlayerResult where id =:resultId")
    fun getHistoryById(resultId: Int): ResultGroupEntity


    @Query("Select * from PlayerResult")
    fun getAll(): List<PlayerResultEntity>

    @Insert
    fun addResultGroup(playerResultEntity: PlayerResultEntity)

    @Delete
    fun deleteResultGroup(playerResultEntities: PlayerResultEntity)

}