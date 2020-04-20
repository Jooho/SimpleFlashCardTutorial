package com.jhouse.simpleflashcard.svc

import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionFile
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionServiceTest : RootTest() {
    private val questionService = QuestionService.getInstance(context)

    @Test
    fun testCreateQuestions() {
        // Test Data Setup
        val expectedQuestionFile = QuestionFile(assetManager.open("quiz.xlsx"), "quiz.xlsx")

        val expectedTopicEntities = listOf<TopicEntity>(
            TopicEntity(1, "별자리", "quiz.xlsx"),
            TopicEntity(2, "TEST", "quiz.xlsx")
        )
        val expectedLastQuestionEntities = setOf<QuestionEntity>(
            QuestionEntity(1, 1, "Q1", "A1"),
            QuestionEntity(2, 1, "Q2", "A2"),
            QuestionEntity(3, 1, "Q3", "A3"),
            QuestionEntity(4, 1, "Q4", "A4/A5")
        )
        expectedTopicEntities.forEach {
            db.topicDao().insertTopic(it)
        }
        // Test Scenario(When)
        val addedTopicQuestionsEntities =
            questionService.createQuestions(expectedQuestionFile.loadXLSFile().getSheetAt(1), 2)

        // Expected result (assert)
        val cursor = sqlExecutor.selectByWhere("Question", "Question.topic_id=2")
        cursor.moveToLast()
        assertEquals(
            expectedLastQuestionEntities.size,
            cursor.count
        )
        assertEquals(
            expectedLastQuestionEntities.last().desc,
            cursor.getString(cursor.getColumnIndex("desc"))
        )
        assertEquals(
            expectedLastQuestionEntities.last().answer,
            cursor.getString(cursor.getColumnIndex("answer"))
        )

    }
}