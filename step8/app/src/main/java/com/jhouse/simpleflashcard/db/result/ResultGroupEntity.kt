package com.jhouse.simpleflashcard.db.result

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class ResultGroupEntity(
    @Embedded() val playerResult: PlayerResultEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "resultGroup_id",
        entity = ResultEntity::class
    ) val results: List<ResultEntity>
) {}