package com.jhouse.simpleflashcard.dto

import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionFile
import com.jhouse.simpleflashcard.topic.TopicManager
import org.junit.Assert.assertEquals
import org.junit.Test


class TopicTest: RootTest() {
    private val topicManager = TopicManager.getInstance(context)
    @Test
    fun fetchAllPossibleAnswersByTopic() {
        // Test Data Setup
        var expectedAllAnswers = setOf<String>("A1", "A2", "A3", "A4", "A5")

        // Test Scenario(When)
        topicManager.addTopicsFromQuestionFile(context,
            QuestionFile(assetManager.open(testDateFile),"assets/quiz.xlsx")
        )
        val topic = topicManager.getTopicByName("TEST")

        // Expected result (assert)
        assertEquals(expectedAllAnswers, topic.allAnswers)
    }

    @Test
    fun fetchWrongAnswersByQuestion() {
        // Test Data Setup
        var expectedWrongAnswers = setOf<String>("A2", "A3", "A4", "A5")

        // Test Scenario(When)
        topicManager.addTopicsFromQuestionFile(context,
            QuestionFile(assetManager.open(testDateFile),"assets/quiz.xlsx")
        )

        val topic = topicManager.getTopicByName("TEST")
        val possibleAnswer = topic.getWrongAnswers(topic.questions.elementAt(0))

        // Expected result (assert)
        assertEquals(expectedWrongAnswers , possibleAnswer)
    }


}