package com.jhouse.simpleflashcard.topic

import com.jhouse.simpleflashcard.TestUtil
import com.jhouse.simpleflashcard.question.Question
import com.jhouse.simpleflashcard.question.QuestionFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class TopicManagerTest {
    @BeforeEach
    fun `Reset TopicManager`(){
        TopicManager.cleanTopics()
    }
    @Test
    fun `Get Topics`() {
        // Test Data Setup
        val expectedSize = 0
        // Test Scenario(When)
        val topics: Set<Topic> = TopicManager.getTopics()

        // Expected result (assert)
        assertEquals(expectedSize, topics.size)
    }

    @Test
    fun `Add topics by loading questionFile`() {
        // Test Data Setup
        val testTopicName = "별자리"
        val testTopicsCount = 2
        val testDateFile = "test/quiz.xlsx"
        val testSheet = TestUtil.getFirstSheet(testDateFile)
        println(testSheet.sheetName)
        // Test Scenario(When)

        TopicManager.loadFromQuestionFile(QuestionFile("/home/jooho/dev/Private_Git/flashcard-api/src/test/resources/quiz.xlsx"))
        var topics: Set<Topic> = TopicManager.getTopics()
        // Expected result (assert)

        assertEquals(testTopicsCount, topics.size)
        assertEquals(testSheet.sheetName, topics.elementAt(0).name)
    }

    @Test
    fun `Add topic directly`() {
        // Test Data Setup
        val expectedTopicCount=1
        val testTopic = Topic("ADD_TOPIC_TEST", setOf(Question(1,"Q1", "1"), Question(2,"Q1", "2"), Question(3,"Q1", "3"), Question(4,"Q1", "4"), Question(5,"Q1", "4/5")))
        // Test Scenario(When)
        TopicManager.addTopic(testTopic)

        // Expected result (assert)
        assertEquals(expectedTopicCount, TopicManager.getTopics().size)
        assertEquals(testTopic.questions.size, TopicManager.getTopics().filter{ topic ->
            topic.name == "ADD_TOPIC_TEST"
        }[0].questions.size)
    }

    @Test
    fun `Get specific topic by name `() {
        // Test Data Setup
        val expectedTopic = Topic("ADD_TOPIC_TEST", setOf(Question(1,"Q1", "1"), Question(2,"Q1", "2"), Question(3,"Q1", "3"), Question(4,"Q1", "4"), Question(5,"Q1", "4/5")))

        // Test Scenario(When)
        // Dependence: addTopic
        TopicManager.addTopic(expectedTopic)
        val actualTopic = TopicManager.getTopicByName("ADD_TOPIC_TEST")
        // Expected result (assert)
        assertEquals(expectedTopic.name, actualTopic.name)
        assertEquals(expectedTopic.questions.size, actualTopic.questions.size)
    }


}