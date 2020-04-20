package com.jhouse.simpleflashcard.db.topic

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.jhouse.simpleflashcard.db.question.QuestionEntity

@Entity
data class TopicQuestionsEntity(
    @Embedded() val topic: TopicEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "topic_id",
        entity = QuestionEntity::class
    ) val questions: List<QuestionEntity>
) {}