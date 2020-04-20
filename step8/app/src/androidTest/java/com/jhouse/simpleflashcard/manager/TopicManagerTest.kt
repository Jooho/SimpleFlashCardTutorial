package com.jhouse.simpleflashcard.manager

import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.AndroidTestUtil
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionFile
import com.jhouse.simpleflashcard.topic.Topic
import com.jhouse.simpleflashcard.topic.TopicFactory
import com.jhouse.simpleflashcard.topic.TopicManager
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals


class TopicManagerTest : RootTest() {
    private val topicManager = TopicManager.getInstance(context)

    @Test
    fun testGetTopics() {
        // Test Data Setup
        var expectedSize = 0
        // Test Scenario(When)
        var topics: Set<Topic> = topicManager.getTopics()
        // Expected result (assert)
        assertEquals(expectedSize, topics.size)

        // Test Data Setup
        topicManager.addTopicsFromQuestionFile(
            context,
            QuestionFile(assetManager.open("quiz.xlsx"), "quiz.xlsx")
        )
        expectedSize = 2
        // Test Scenario(When)
        topics = topicManager.getTopics()

        // Expected result (assert)
        assertEquals(
            expectedSize, topics.size
        )
    }

    @Test
    fun testAddTopicsByLoadingQuestionFile() {
        // Test Data Setup
        val testTopicName = "별자리"
        val testTopicsCount = 2
        val testSheet =
            AndroidTestUtil.getFirstSheet(assetManager.open(testDateFile), testTopicName, context)
        val expectedQuestionEntities =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )
        // Test Scenario(When)
        topicManager.addTopicsFromQuestionFile(
            context,
            QuestionFile(assetManager.open(testDateFile), "assets/quiz.xlsx")
        )

        // Expected result (assert)
        var actualTopics: Set<Topic> = topicManager.getTopics()
        assertEquals(testTopicsCount, actualTopics.size)
        assertEquals(testSheet.sheetName, actualTopics.first().name)
        assertEquals(expectedQuestionEntities.last().desc, actualTopics.last().questions.last().desc)
    }

    @Test
    fun testGetTopicByName() {
        // Test Data Setup
        val testTopicName = "TEST"
            AndroidTestUtil.getFirstSheet(assetManager.open(testDateFile), testTopicName, context)
        val expectedQuestionEntities =
            setOf(
                QuestionEntity(1, 1, "Q1", "A1"),
                QuestionEntity(2, 1, "Q2", "A2"),
                QuestionEntity(3, 1, "Q3", "A3"),
                QuestionEntity(4, 1, "Q4", "A4/A5")
            )
        topicManager.addTopicsFromQuestionFile(
            context,
            QuestionFile(assetManager.open(testDateFile), "assets/quiz.xlsx")
        )

        // Test Scenario(When)
        val actualTopic = topicManager.getTopicByName(testTopicName)
        // Expected result (assert)
        assertEquals(testTopicName, actualTopic.name)
        assertEquals(expectedQuestionEntities.size, actualTopic.questions.size)
        assertEquals(expectedQuestionEntities.last().desc, actualTopic.questions.last().desc)
    }

    @Test
    fun testGetTopicNames(){
        // Test Data Setup
        topicManager.addTopicsFromQuestionFile(
            context,
            QuestionFile(assetManager.open(testDateFile), "assets/quiz.xlsx")
        )

        // Test Scenario(When)
        val actualTopics = topicManager.getTopicNames()

        // Expected result (assert)
        assertEquals(2, actualTopics.size)
        assertEquals(setOf("별자리","TEST"), actualTopics)
    }

    @Test
    fun testInitTopicManager() {
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
        topicManager.initTopicManager()

        // Expected result (assert)
        val actualTopics = topicManager.getTopics()
        assertEquals(expectedTopicEntities.size,actualTopics.size)
        assertEquals(expectedTopicEntities.last().name,actualTopics.last().name)
        assertEquals(expectedFirstQuestionEntities.first().desc,actualTopics.first().questions.first().desc)
        assertEquals(expectedLastQuestionEntities.last().desc,actualTopics.last().questions.last().desc)

    }

    @Test
    fun testGetQuestionsWithPlayerConfig(){
        val expectedQuestionCount = 10
        var expectedFirstQuestionEntities = mutableSetOf<QuestionEntity>()
        expectedFirstQuestionEntities.add(QuestionEntity(1, 1, "양/활동/불/화성", "백양자리"))
        expectedFirstQuestionEntities.add(QuestionEntity(2, 1, "양기의 에너지가 승하기 시작2", "백양자리2"))
        expectedFirstQuestionEntities.add(QuestionEntity(3, 1, "양기의 에너지가 승하기 시작3", "백양자리3"))
        expectedFirstQuestionEntities.add(QuestionEntity(4, 1, "양기의 에너지가 승하기 시작4", "백양자리4"))
        expectedFirstQuestionEntities.add(QuestionEntity(5, 1, "양기의 에너지가 승하기 시작5", "백양자리5"))
        expectedFirstQuestionEntities.add(QuestionEntity(6, 1, "양기의 에너지가 승하기 시작6", "백양자리6"))
        expectedFirstQuestionEntities.add(QuestionEntity(7, 1, "양기의 에너지가 승하기 시작7", "백양자리7"))
        expectedFirstQuestionEntities.add(QuestionEntity(8, 1, "양기의 에너지가 승하기 시작8", "백양자리8"))
        expectedFirstQuestionEntities.add(QuestionEntity(9, 1, "양기의 에너지가 승하기 시작9", "백양자리9"))
        expectedFirstQuestionEntities.add(QuestionEntity(10, 1, "양기의 에너지가 승하기 시작10", "백양자리10"))
        expectedFirstQuestionEntities.add(QuestionEntity(11, 1, "양기의 에너지가 승하기 시작11", "백양자리11"))
        expectedFirstQuestionEntities.add(QuestionEntity(12, 1, "양기의 에너지가 승하기 시작12", "백양자리12"))

        val expectedTopicEntities = mutableListOf<TopicEntity>()
        expectedTopicEntities.add(TopicEntity(1, "별자리", "quiz.xlsx"))

        expectedTopicEntities.forEach {
            db.topicDao().insertTopic(it)
        }
        db.questionDao().insertQuestions(expectedFirstQuestionEntities)
        topicManager.initTopicManager()

        val expectedTopic = TopicFactory.createTopic(db.topicDao().getTopics().first())
        // Test Scenario(When)
        val actualQuestions = topicManager.getQuestionsWithPlayerConfig("별자리","default")


        println(expectedTopic.questions)
            println(actualQuestions.first())
        // Expected result (assert)
        assertEquals(expectedQuestionCount,actualQuestions.size)
        assertTrue(expectedTopic.questions.any { it.desc == actualQuestions.first().desc })
        assertTrue(expectedTopic.questions.any { it.desc == actualQuestions.last().desc })


    }

}