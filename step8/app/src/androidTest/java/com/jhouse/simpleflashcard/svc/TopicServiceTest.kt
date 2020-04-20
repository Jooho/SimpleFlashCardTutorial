package com.jhouse.simpleflashcard.svc

import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionFile
import org.junit.Assert.assertEquals
import org.junit.Test

class TopicServiceTest : RootTest() {

    private val topicService = TopicService.getInstance(context)

    @Test
    fun testAddTopicsFromQuestionFile() {
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

        // Test Scenario(When)
        val addedTopicQuestionsEntities =
            topicService.addTopicsFromQuestionFile(expectedQuestionFile)

        // Expected result (assert)
        assertEquals(expectedTopicEntities.size, addedTopicQuestionsEntities.size)
        assertEquals(
            expectedLastQuestionEntities.size,
            addedTopicQuestionsEntities.last().questions.size
        )
        assertEquals(
            expectedLastQuestionEntities.last().desc,
            addedTopicQuestionsEntities.last().questions.last().desc
        )
        assertEquals(
            expectedLastQuestionEntities.last().answer,
            addedTopicQuestionsEntities.last().questions.last().answer
        )

    }

    @Test
    fun testGetTopics() {
        // Test Data Setup
        var expectedFirstQuestionEntities = mutableSetOf<QuestionEntity>()
        expectedFirstQuestionEntities.add(QuestionEntity(1, 1, "양/활동/불/화성", "백양자리"))
        expectedFirstQuestionEntities.add(QuestionEntity(2, 1, "양기의 에너지가 승하기 시작", "백양자리"))
        expectedFirstQuestionEntities.add(
            QuestionEntity(
                3,
                1,
                "선두에 이끄는 성향. 개척정신이 강함",
                "백양자리/화성/불/활동"
            )
        )
        expectedFirstQuestionEntities.add(
            QuestionEntity(
                143,
                1,
                "Bad: 우유부단, 중독성, 속임수, 무절제",
                "물고기자리"
            )
        )
        var expectedLastQuestionEntities = mutableSetOf<QuestionEntity>()
        expectedLastQuestionEntities.add(QuestionEntity(4, 2, "Q4", "A4/A5"))

        val expectedTopicEntities = mutableListOf<TopicEntity>()
        expectedTopicEntities.add(TopicEntity(1, "별자리", "quiz.xlsx"))
        expectedTopicEntities.add(TopicEntity(2, "TEST", "quiz.xlsx"))

        expectedTopicEntities.forEach {
            db.topicDao().insertTopic(it)
        }
        db.questionDao().insertQuestions(expectedFirstQuestionEntities)
        db.questionDao().insertQuestions(expectedLastQuestionEntities)

        // Test Scenario(When)
        val addedTopicQuestionsEntities = topicService.getTopics()

        // Expected result (assert)
        assertEquals(expectedTopicEntities.size, addedTopicQuestionsEntities.size)
        assertEquals(
            expectedFirstQuestionEntities.size,
            addedTopicQuestionsEntities.first().questions.size
        )
        assertEquals(
            expectedFirstQuestionEntities.last().desc,
            addedTopicQuestionsEntities.first().questions.last().desc
        )
        assertEquals(
            expectedFirstQuestionEntities.last().answer,
            addedTopicQuestionsEntities.first().questions.last().answer
        )
        assertEquals(
            expectedLastQuestionEntities.size,
            addedTopicQuestionsEntities.last().questions.size
        )
        assertEquals(
            expectedLastQuestionEntities.last().desc,
            addedTopicQuestionsEntities.last().questions.last().desc
        )
        assertEquals(
            expectedLastQuestionEntities.last().answer,
            addedTopicQuestionsEntities.last().questions.last().answer
        )


    }

    @Test
    fun testDuplicatedTopicChecker() {

    }
}