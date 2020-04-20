package com.jhouse.simpleflashcard.db

import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerResultDaoTest : RootTest() {

    @Test
    fun addResultGroup(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)

        // Test Scenario(When)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)


        var cursor = sqlExecutor.select("PlayerResult")
        cursor.moveToFirst()

        // Expected result (assert)
        assertEquals(expectedResultGroupEntity.id, cursor.getInt(cursor.getColumnIndex("id")))
        assertEquals(
            expectedResultGroupEntity.playerId,
            cursor.getInt(cursor.getColumnIndex("player_id"))
        )
        assertEquals(
            expectedResultGroupEntity.topicId,
            cursor.getInt(cursor.getColumnIndex("topic_id"))
        )

    }

    @Test
    fun getResultGroups(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)

        // Test Scenario(When)
        val resultGroups = db.resultGroupDao().getResultGroups()

        // Expected result (assert)
        assertEquals(expectedResultGroupEntity.id, resultGroups.first().playerResult.id)
        assertEquals(
            expectedResultGroupEntity.playerId,
            resultGroups.first().playerResult.playerId
        )
        assertEquals(
            expectedResultGroupEntity.topicId,
            resultGroups.first().playerResult.topicId
        )

    }

    @Test
    fun getResultGroupByPlayerId(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)

        // Test Scenario(When)
        val resultGroups = db.resultGroupDao().getResultGroupByPlayerId(expectedResultGroupEntity.playerId)

        // Expected result (assert)
        assertEquals(expectedResultGroupEntity.id, resultGroups.playerResult.id)
        assertEquals(
            expectedResultGroupEntity.playerId,
            resultGroups.playerResult.playerId
        )
        assertEquals(
            expectedResultGroupEntity.topicId,
            resultGroups.playerResult.topicId
        )
    }

    @Test
    fun deleteResultGroup(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroup = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResult = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroup)
        // Pre requirement Assert
        var cursor = sqlExecutor.select("PlayerResult")
        cursor.moveToFirst()

        assertEquals(expectedResultGroup.id, cursor.getInt(cursor.getColumnIndex("id")))
        assertEquals(
            expectedResultGroup.playerId,
            cursor.getInt(cursor.getColumnIndex("player_id"))
        )
        assertEquals(
            expectedResultGroup.topicId,
            cursor.getInt(cursor.getColumnIndex("topic_id"))
        )

        // Test Scenario(When)
        db.resultGroupDao().deleteResultGroup(expectedResultGroup)

        // Expected result (assert)
        cursor = sqlExecutor.select("PlayerResult")
        assertEquals(
            0,
            cursor.count
        )

    }
}