package com.jhouse.simpleflashcard.db

import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TopicDaoTest : RootTest() {


    @Test
    fun testAddTopic() {
        // Test Data Setup
        val expectedTopicCount = 1
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntity =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        // Test Scenario(When)
        db.topicDao().insertTopic(expectedTopicEntity)

        // Expected result (assert)
        val cursor = sqlExecutor.select("Topic")
        cursor.moveToFirst()
        assertEquals(expectedTopicCount, cursor.count)
        assertEquals(expectedTopicEntity.name, cursor.getString(cursor.getColumnIndex("name")))
    }



    @Test
    fun testGetTopics() {
        // Test Data Setup
        val expectedTopicCount = 1
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntity =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        db.topicDao().insertTopic(expectedTopicEntity)
        db.questionDao().insertQuestions(expectedQuestionEntity)

        // Test Scenario(When)
        val actualTopicEntities = db.topicDao().getTopics()

        // Expected result (assert)
        assertEquals(expectedTopicCount, actualTopicEntities.size)
        assertEquals(expectedTopicEntity.name, actualTopicEntities.first().topic.name)
        assertEquals(expectedQuestionEntity.first().desc, actualTopicEntities.first().questions.first().desc)
    }

    @Test
    fun testGetTopicByName() {
        // Test Data Setup
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntity =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        db.topicDao().insertTopic(expectedTopicEntity)
        db.questionDao().insertQuestions(expectedQuestionEntity)

        // Test Scenario(When)
        val actualTopicEntity = db.topicDao().getTopicByName(expectedTopicEntity.name)

        // Expected result (assert)
        assertEquals(expectedTopicEntity.name, actualTopicEntity.topic.name)
        assertEquals(expectedQuestionEntity.first().desc, actualTopicEntity.questions.first().desc)
    }


    @Test
    fun testGetTopicQuestionsById() {
        // Test Data Setup
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntity =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        db.topicDao().insertTopic(expectedTopicEntity)
        db.questionDao().insertQuestions(expectedQuestionEntity)

        // Test Scenario(When)
        val actualTopicEntity = db.topicDao().getTopicById(expectedTopicEntity.id)

        // Expected result (assert)
        assertEquals(expectedTopicEntity.name, actualTopicEntity.topic.name)
        assertEquals(expectedQuestionEntity.first().desc, actualTopicEntity.questions.first().desc)
    }

    @Test
    fun testDeleteTopic() {
        // Test Data Setup
        val expectedCountAfterInsertTopic = 1
        val expectedCountAfterInsertQuestion = 4
        val expectedCountAfterDelete = 0
        val expectedTopicEntity = TopicEntity(
            1,
            "ADD_TOPIC_TEST", "test.xlsx"
        )
        val expectedQuestionEntity =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )

        db.topicDao().insertTopic(expectedTopicEntity)
        db.questionDao().insertQuestions(expectedQuestionEntity)

        // Expected result (assert)
        val cursorAfterInsertedTopic = sqlExecutor.select("Topic")
        cursorAfterInsertedTopic.moveToFirst()
        assertEquals(expectedCountAfterInsertTopic, cursorAfterInsertedTopic.count)
        assertEquals(expectedTopicEntity.name, cursorAfterInsertedTopic.getString(cursorAfterInsertedTopic.getColumnIndex("name")))
        val cursorAfterInsertQuestion = sqlExecutor.select("Question")
        assertEquals(expectedCountAfterInsertQuestion,cursorAfterInsertQuestion.count)

        // Test Scenario(When)
        db.topicDao().deleteTopic(expectedTopicEntity)

        // Expected result (assert)
        val cursorAfterDeleteTopic = sqlExecutor.select("Topic")
        assertEquals(expectedCountAfterDelete,cursorAfterDeleteTopic.count)

        //Questions must be deleted automatically by SQL Constraints
        val cursorAfterDeleteQuestion = sqlExecutor.select("Question")
        assertEquals(expectedCountAfterDelete,cursorAfterDeleteQuestion.count)
    }
}