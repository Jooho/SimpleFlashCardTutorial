package com.jhouse.simpleflashcard.db.config

import androidx.room.*
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.question.QuestionType

@Entity(
    tableName = "Config",indices = [Index(value = ["player_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = PlayerEntity::class,
        parentColumns = ["id"],
        childColumns = ["player_id"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class ConfigEntity(
    @PrimaryKey @ColumnInfo(name = "player_id") var playerId: Int,
    @ColumnInfo(name = "question_type") var questionType: QuestionType,
    @ColumnInfo(name = "time_limit") var timeLimitSecs: Int,
    @ColumnInfo(name = "question_count") var questionCount: Int,
    @ColumnInfo(name = "save_result") var saveResult: Boolean
) {


}