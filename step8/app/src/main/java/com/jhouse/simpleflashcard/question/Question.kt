package com.jhouse.simpleflashcard.question

import com.jhouse.simpleflashcard.db.question.QuestionEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Question(questionEntity: QuestionEntity) {
    private val logger: Logger = LoggerFactory.getLogger(Question::class.java)

    constructor(id: Int, topicId: Int,desc: String, answer: String) : this(
        QuestionEntity(
            id,
            topicId,
            desc,
            answer
        )
    )

    val id: Int = questionEntity.id
    val desc: String = questionEntity.desc
    private val answer: String = questionEntity.answer

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