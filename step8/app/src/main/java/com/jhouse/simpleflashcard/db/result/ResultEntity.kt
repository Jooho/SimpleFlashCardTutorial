package com.jhouse.simpleflashcard.db.result

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Result", primaryKeys = ["resultGroup_id", "question_id"],
    foreignKeys = [ForeignKey(
        entity = PlayerResultEntity::class,
        parentColumns = ["id"],
        childColumns = ["resultGroup_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ResultEntity(
    @ColumnInfo(name = "resultGroup_id",index = true) var resultGroupId: Int,
    @ColumnInfo(name = "question_id") var questionId: Int,
    @ColumnInfo(name = "response_answer") var responseAnswer: String,
    @ColumnInfo(name = "right_answer") var rightAnswer: String,
    var correct: Boolean
) {

}