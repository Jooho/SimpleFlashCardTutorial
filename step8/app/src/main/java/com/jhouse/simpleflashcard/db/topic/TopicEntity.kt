package com.jhouse.simpleflashcard.db.topic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Topic", inheritSuperIndices = true)
data class TopicEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "file_path") var filePath: String
) {}