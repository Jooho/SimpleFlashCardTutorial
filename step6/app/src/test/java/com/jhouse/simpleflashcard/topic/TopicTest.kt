package com.jhouse.simpleflashcard.topic

import com.jhouse.simpleflashcard.TestUtil
import com.jhouse.simpleflashcard.question.QuestionFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("실제적인 질문과답에 대한 테스트")
class TopicTest {

    val testDateFile = "test/quiz.xlsx"

    @BeforeEach
    fun `clean all`(){
        TopicManager.cleanTopics()
    }
    @Test
    fun `Fetch possible answers by Topic`() {
        // Test Data Setup
        var expectedAllAnswers = setOf<String>("A1", "A2", "A3", "A4", "A5")

        // Test Scenario(When)
        TopicManager.loadFromQuestionFile(QuestionFile(TestUtil.getTestResourceFilePathString(testDateFile)))
        val topic = TopicManager.getTopicByName("TEST")

        // Expected result (assert)
        assertEquals(expectedAllAnswers, topic.allAnswers)
    }

    @Test
    fun `Fetch possible answers by Question`() {
        // Test Data Setup
        var expectedWrongAnswers = setOf<String>("A2", "A3", "A4", "A5")
        // Test Scenario(When)
        TopicManager.loadFromQuestionFile(QuestionFile(TestUtil.getTestResourceFilePathString(testDateFile)))
        val topic = TopicManager.getTopicByName("TEST")
        val possibleAnswer = topic.getWrongAnswers(topic.questions.elementAt(0))

        // Expected result (assert)
        assertEquals(expectedWrongAnswers , possibleAnswer)
    }


}