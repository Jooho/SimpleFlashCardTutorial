package com.jhouse.simpleflashcard.db.result

import androidx.room.*
import com.jhouse.simpleflashcard.db.player.PlayerEntity

@Entity(
    tableName = "PlayerResult", indices = [Index(value = ["id","player_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = PlayerEntity::class,
        parentColumns = ["id"],
        childColumns = ["player_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PlayerResultEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "player_id", index = true) var playerId: Int,
    @ColumnInfo(name = "topic_id") var topicId: Int,
    @ColumnInfo(name = "date") var date: String
) {

}