package com.jhouse.simpleflashcard.db.result

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {

    @Insert
    fun addResult(results: ResultEntity)

    @Insert
    fun addResults(results: List<ResultEntity>)

    @Query("Select * From Result, PlayerResult where Result.resultGroup_id = :resultGroupId")
    fun getResultsByResultGroupId(resultGroupId: String): List<ResultEntity>

    @Delete
    fun deleteResult(resultEntity: ResultEntity)

    @Delete
    fun deleteResults(resultEntity: List<ResultEntity>)



}