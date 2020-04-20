package com.jhouse.simpleflashcard.svc

import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import org.junit.Assert
import org.junit.Test

class ResultServiceTest : RootTest(){
    private var resultService = ResultService.getInstance(context)

    @Test
    fun testAddResults(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)

        // Test Scenario(When)
        val results = resultService.addResults(listOf(expectedResultEntity))

        var cursor = sqlExecutor.select("Result")
        cursor.moveToFirst()

        // Expected result (assert)
        Assert.assertEquals(1, cursor.count)

        Assert.assertEquals(
            expectedResultEntity.resultGroupId,
            cursor.getInt(cursor.getColumnIndex("resultGroup_id"))
        )
        Assert.assertEquals(
            expectedResultEntity.questionId,
            cursor.getInt(cursor.getColumnIndex("question_id"))
        )
        Assert.assertEquals(
            expectedResultEntity.responseAnswer,
            cursor.getString(cursor.getColumnIndex("response_answer"))
        )

        Assert.assertEquals(
            expectedResultEntity.rightAnswer,
            cursor.getString(cursor.getColumnIndex("right_answer"))
        )

    }
}