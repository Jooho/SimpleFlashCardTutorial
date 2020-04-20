package com.jhouse.simpleflashcard.db.question

import androidx.room.*
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity

@Entity(tableName = "Question", primaryKeys = ["id", "topic_id"],indices = [Index(value = ["id","topic_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = TopicEntity::class,
        parentColumns = ["id"],
        childColumns = ["topic_id"],
        onDelete = ForeignKey.CASCADE
    )])
data class QuestionEntity(
    val id: Int,
    @ColumnInfo(name = "topic_id", index = true) val topic_id: Int,
    @ColumnInfo(name = "desc") val desc: String,
    @ColumnInfo(name = "answer") val answer: String
) {}