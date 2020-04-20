package com.jhouse.simpleflashcard.db.topic

import androidx.room.*

@Dao
interface TopicDao {
    @Insert
    fun insertTopic(topicEntity: TopicEntity)

    @Transaction
    @Query("Select * from Topic where name = :topicName")
    fun getTopicByName(topicName: String): TopicQuestionsEntity

    @Transaction
    @Query("Select * from Topic")
    fun getTopics(): List<TopicQuestionsEntity>

    @Transaction
    @Query("Select * from Topic where id = :topicId")
    fun getTopicById(topicId: Int): TopicQuestionsEntity

    @Delete
    fun deleteTopic(topicEntity: TopicEntity)


}