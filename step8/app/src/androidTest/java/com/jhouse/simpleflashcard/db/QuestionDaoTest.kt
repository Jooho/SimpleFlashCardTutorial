package com.jhouse.simpleflashcard.db

import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionDaoTest : RootTest() {
    @Test
    fun testInsertQuestions(){
        // Test Data Setup
        val expectedQuestionCount = 4
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntities =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )
        db.topicDao().insertTopic(expectedTopicEntity)

        // Test Scenario(When)
        db.questionDao().insertQuestions(expectedQuestionEntities)

        // Expected result (assert)
        val cursor = sqlExecutor.select("Question")
        cursor.moveToFirst()
        assertEquals(expectedQuestionCount, cursor.count)
        assertEquals(expectedQuestionEntities.first().desc, cursor.getString(cursor.getColumnIndex("desc")))
    }

    @Test
    fun testGetQuestions(){
        // Test Data Setup
        val expectedQuestionCount = 4
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntities =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        db.topicDao().insertTopic(expectedTopicEntity)
        db.questionDao().insertQuestions(expectedQuestionEntities)

        // Test Scenario(When)
        val actualQuestionEntities = db.questionDao().getQuestions()


        // Expected result (assert)
        assertEquals(expectedQuestionCount, actualQuestionEntities.size)
        assertEquals(expectedQuestionEntities.first().desc, actualQuestionEntities.first().desc)
        assertEquals(expectedQuestionEntities.first().answer, actualQuestionEntities.first().answer)

    }

    @Test
    fun testDeleteQuestions(){
        // Test Data Setup
        val expectedCountAfterInsertQuestion = 4
        val expectedCountAfterDelete = 0
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntities =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        db.topicDao().insertTopic(expectedTopicEntity)
        db.questionDao().insertQuestions(expectedQuestionEntities)

        // Expected result (assert)
        val cursorAfterInsertQuestion = sqlExecutor.select("Question")
        assertEquals(expectedCountAfterInsertQuestion,cursorAfterInsertQuestion.count)

        // Test Scenario(When)
        db.questionDao().deleteQuestions(expectedQuestionEntities)

        // Expected result (assert)
        val cursorAfterDeleteQuestion = sqlExecutor.select("Question")
        assertEquals(expectedCountAfterDelete,cursorAfterDeleteQuestion.count)
    }


}