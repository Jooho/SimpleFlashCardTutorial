package com.jhouse.simpleflashcard.question

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Question(val id: Int, val desc: String, private val answer: String) {
    private val logger: Logger = LoggerFactory.getLogger(Question::class.java)

    var answers = mutableSetOf<String>()

    init {
        answer.split("/").forEach { v ->
            answers.add(v)
        }
        if (logger.isDebugEnabled) {
            logger.debug("Answers for question({})", desc)
            answers.forEach {
                logger.debug(it)
            }
        }

    }


}