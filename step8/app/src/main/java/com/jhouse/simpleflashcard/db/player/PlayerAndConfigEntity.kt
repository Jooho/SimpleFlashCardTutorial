package com.jhouse.simpleflashcard.db.player

import androidx.room.Embedded
import androidx.room.Relation
import com.jhouse.simpleflashcard.db.config.ConfigEntity


class PlayerAndConfigEntity(
    @Embedded() val player: PlayerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "player_id",
        entity = ConfigEntity::class
    ) val config: ConfigEntity
) {
}
