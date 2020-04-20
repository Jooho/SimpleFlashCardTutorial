package com.jhouse.simpleflashcard.db.config

import androidx.room.*

@Dao
interface ConfigDao {

    @Insert
    fun insertConfig(configEntity: ConfigEntity)

    @Query("Select * from Config")
    fun getConfigs(): List<ConfigEntity>

    @Query("Select * from Config where player_id=:playId")
    fun getConfigByPlayerId(playId: Int): ConfigEntity

    @Update
    fun updateConfig(configEntity: ConfigEntity)

    @Delete
    fun deleteConfig(configEntity: ConfigEntity)
}