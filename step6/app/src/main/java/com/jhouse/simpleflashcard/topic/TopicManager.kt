package com.jhouse.simpleflashcard.topic

import com.jhouse.simpleflashcard.question.QuestionFile
import java.io.FileInputStream

object TopicManager {
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
//            throw noTopicException
//        }
        return topic[0]


    }
}