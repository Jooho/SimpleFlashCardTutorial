package com.jhouse.simpleflashcard.db

import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultDaoTest: RootTest() {

    @Test
    fun addResults(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)

        // Test Scenario(When)
        val results = db.resultDao().addResult(expectedResultEntity)

        var cursor = sqlExecutor.select("Result")
        cursor.moveToFirst()

        // Expected result (assert)
        assertEquals(1, cursor.count)

        assertEquals(
            expectedResultEntity.resultGroupId,
            cursor.getInt(cursor.getColumnIndex("resultGroup_id"))
        )
        assertEquals(
            expectedResultEntity.questionId,
            cursor.getInt(cursor.getColumnIndex("question_id"))
        )
        assertEquals(
            expectedResultEntity.responseAnswer,
            cursor.getString(cursor.getColumnIndex("response_answer"))
        )

        assertEquals(
            expectedResultEntity.rightAnswer,
            cursor.getString(cursor.getColumnIndex("right_answer"))
        )
    }

    @Test
    fun getResultsByResultGroupId(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroup = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResult = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroup)

        // Test Scenario(When)
        val results = db.resultDao().addResult(expectedResult)

        var cursor = sqlExecutor.select("Result")
        cursor.moveToFirst()

        // Expected result (assert)
        assertEquals(1, cursor.count)

        assertEquals(
            expectedResult.resultGroupId,
            cursor.getInt(cursor.getColumnIndex("resultGroup_id"))
        )
        assertEquals(
            expectedResult.questionId,
            cursor.getInt(cursor.getColumnIndex("question_id"))
        )
        assertEquals(
            expectedResult.responseAnswer,
            cursor.getString(cursor.getColumnIndex("response_answer"))
        )

        assertEquals(
            expectedResult.rightAnswer,
            cursor.getString(cursor.getColumnIndex("right_answer"))
        )

    }

    @Test
    fun deleteResults(){
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroup = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResult = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroup)

        // Test Scenario(When)
        val results = db.resultDao().deleteResults(listOf(expectedResult))

        // Expected result (assert)
        var cursor = sqlExecutor.select("Result")
        assertEquals(0, cursor.count)

    }
}