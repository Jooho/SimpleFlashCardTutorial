package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.topic.TopicManager
import org.apache.poi.ss.usermodel.Sheet
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class QuestionService private constructor(context: Context) {
    private val logger: Logger = LoggerFactory.getLogger(TopicManager::class.java)

    private var db: FlashCardDB = FlashCardDB.getInstance(context)

    companion object : SingletonHolder<QuestionService, Context>(::QuestionService)

    fun createQuestions(sheet: Sheet, topicNum: Int): Unit {
        var questionId = 1
        var questionEntities = mutableSetOf<QuestionEntity>()

        sheet.rowIterator().forEach {
            if (it.getCell(0).toString() !in setOf("Question", "")) {
                questionEntities.add(
                    QuestionEntity(
                        questionId++, topicNum,
                        it.getCell(0).toString(),
                        it.getCell(1).toString()
                    )
                )
            }
        }

        db.questionDao().insertQuestions(questionEntities.toSet())

        if (logger.isTraceEnabled) {
            logger.trace("Created Questions")
            questionEntities.forEach {
                logger.trace("question -> id: {}, desc: {},answer: {}", it.id, it.desc, it.answer)
            }
        }
    }

}