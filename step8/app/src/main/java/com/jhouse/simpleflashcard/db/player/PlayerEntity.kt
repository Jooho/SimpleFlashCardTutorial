package com.jhouse.simpleflashcard.db.player

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "Player", primaryKeys = ["id"], indices = [Index(value = ["id","name"], unique = true)])
data class PlayerEntity(
    var id: Int,
    var name: String
) {}