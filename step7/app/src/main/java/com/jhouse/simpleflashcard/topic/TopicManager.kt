package com.jhouse.simpleflashcard.topic

import android.widget.Toast
import com.jhouse.simpleflashcard.question.QuestionFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TopicManager {
    private val logger: Logger = LoggerFactory.getLogger(TopicManager::class.java)

    private var topics = mutableSetOf<Topic>()

    fun cleanTopics(): Unit {
        topics.clear()
    }

    fun getTopics(): Set<Topic> {
        return topics
    }

    fun getTopicNames(): Set<String> {
        var topicNames = mutableSetOf<String>()
        topics.forEach { topic -> topicNames.add(topic.name) }

        if (logger.isTraceEnabled) {
            logger.trace("Topic names ==> {}", topics.toString())
        }
        return topicNames
    }

    fun loadFromQuestionFile(questionFile: QuestionFile) {
        questionFile.load(topics)
    }

    fun addTopic(topic: Topic) {
        topics.add(topic)

    }

    fun getTopicByName(topicName: String): Topic {
        val topic = topics.filter { topic ->
            topic.name == topicName
        }
//        TODO("If topic does not exist, it needs to throw a Exception")
//        if (topic.size == 0) {
//
//        }
        return topic[0]


    }
}