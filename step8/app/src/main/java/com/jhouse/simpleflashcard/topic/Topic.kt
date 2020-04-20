package com.jhouse.simpleflashcard.topic

import com.jhouse.simpleflashcard.question.Question
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class Topic constructor(
    var id: Int,
    var name: String,
    var questions: Set<Question>,
    var filePath: String
) {
    private val logger: Logger = LoggerFactory.getLogger(Topic::class.java)

    var allAnswers = mutableSetOf<String>()

    constructor(topicNum: Int, name: String, questions: Set<Question>) : this(
        topicNum,
        name,
        questions,
        "noFile"
    )

    init {
        questions.forEach { question ->
            question.answers.forEach {
                allAnswers.add(it)
            }
        }
    }

    fun getWrongAnswers(question: Question): Set<String> {
        return this.allAnswers.subtract(question.answers)
    }
}