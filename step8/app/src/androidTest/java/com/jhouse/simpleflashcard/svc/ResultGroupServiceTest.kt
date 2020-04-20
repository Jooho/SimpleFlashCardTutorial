package com.jhouse.simpleflashcard.svc

import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import org.junit.Assert
import org.junit.Test

class ResultGroupServiceTest: RootTest() {
    private val resultGroupService = ResultGroupService.getInstance(context)

    @Test
    fun getHistory(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroup = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResult = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroup)

        // Test Scenario(When)
        val resultGroups = resultGroupService.getHistory()

        // Expected result (assert)
        Assert.assertEquals(expectedResultGroup.id, resultGroups.first().playerResult.id)
        Assert.assertEquals(
            expectedResultGroup.playerId,
            resultGroups.first().playerResult.playerId
        )
        Assert.assertEquals(
            expectedResultGroup.topicId,
            resultGroups.first().playerResult.topicId
        )

    }

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

        resultGroupService.addResultGroup("default","별자리", listOf(expectedResultEntity))


        var cursor = sqlExecutor.select("PlayerResult")
        cursor.moveToFirst()

        // Expected result (assert)
        Assert.assertEquals(expectedResultGroupEntity.id, cursor.getInt(cursor.getColumnIndex("id")))
        Assert.assertEquals(
            expectedResultGroupEntity.playerId,
            cursor.getInt(cursor.getColumnIndex("player_id"))
        )
        Assert.assertEquals(
            expectedResultGroupEntity.topicId,
            cursor.getInt(cursor.getColumnIndex("topic_id"))
        )
    }
}