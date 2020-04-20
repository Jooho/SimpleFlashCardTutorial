package com.jhouse.simpleflashcard.topic

import com.jhouse.simpleflashcard.db.topic.TopicQuestionsEntity
import com.jhouse.simpleflashcard.question.Question
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TopicFactory {
    private val logger: Logger = LoggerFactory.getLogger(TopicFactory::class.java)

    fun createTopic(topicQuestionsEntity: TopicQuestionsEntity): Topic {
        var questions = mutableSetOf<Question>()
        topicQuestionsEntity.questions.forEach {
            questions.add(Question(it))
        }

        return Topic(
            topicQuestionsEntity.topic.id,
            topicQuestionsEntity.topic.name,
            questions,
            topicQuestionsEntity.topic.filePath
        )

    }


}