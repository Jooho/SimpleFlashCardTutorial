package com.jhouse.simpleflashcard.topic

import android.content.Context
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.player.PlayerManager
import com.jhouse.simpleflashcard.question.Question
import com.jhouse.simpleflashcard.question.QuestionFile
import com.jhouse.simpleflashcard.svc.TopicService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TopicManager private constructor(context: Context) {
    private val logger: Logger = LoggerFactory.getLogger(TopicManager::class.java)

    companion object : SingletonHolder<TopicManager, Context>(::TopicManager)

    private var topics = mutableSetOf<Topic>()

    private val topicService = TopicService.getInstance(context)
    private val playerManager = PlayerManager.getInstance(context)

    fun clearTopics(): Unit {
        topics.clear()
    }

    fun getTopics(): Set<Topic> {
        return topics.toSet()
    }


    fun addTopicsFromQuestionFile(context: Context, questionFile: QuestionFile) {
        topicService.addTopicsFromQuestionFile(questionFile).forEach {
            topics.add(TopicFactory.createTopic(it))
        }
    }

    fun getTopicNames(): Set<String> {
        var topicNames = mutableSetOf<String>()
        topics.forEach { topic -> topicNames.add(topic.name) }

        if (logger.isDebugEnabled) {
            logger.debug("Topic names ==> {}", topics.toString())
        }
        return topicNames.toSet()
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

    fun initTopicManager() {
        topicService.getTopics().forEach {
            topics.add(TopicFactory.createTopic(it))
        }
    }

    fun getQuestionsWithPlayerConfig(topicName: String, playerName: String): Set<Question> {
        val targetTopic = topics.filter {
            it.name == topicName
        }[0]
        val targetPlayer = playerManager.getPlayerByName(playerName)
        val flashCardQuestions = mutableSetOf<Question>()

        for (i in 0 until targetPlayer.config.questionCount) {
            var tempQuestion = targetTopic.questions.random()
            while (flashCardQuestions.contains(tempQuestion)) {
                tempQuestion = targetTopic.questions.random()
            }
            flashCardQuestions.add(tempQuestion)
        }

        return flashCardQuestions.toSet()
    }


}