package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.db.topic.TopicQuestionsEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.question.QuestionFile
import com.jhouse.simpleflashcard.topic.TopicManager
import org.apache.poi.ss.usermodel.Sheet
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TopicService private constructor(context: Context) {

    private val logger: Logger = LoggerFactory.getLogger(TopicManager::class.java)

    private var db: FlashCardDB = FlashCardDB.getInstance(context)
    private val questionService = QuestionService.getInstance(context)

    companion object : SingletonHolder<TopicService, Context>(::TopicService)

    fun addTopicsFromQuestionFile(questionFile: QuestionFile): Set<TopicQuestionsEntity> {
        var sheet: Sheet
        val workbook = questionFile.loadXLSFile()
        val topics = db.topicDao().getTopics()
        val topicSize = topics.size

        var addedTopicQuestionsEntities = mutableSetOf<TopicQuestionsEntity>()
        var lastTopicId = if (topicSize == 0) 1 else topicSize

        for (sheetNumber in 0 until workbook.numberOfSheets.toInt()) {
            sheet = workbook.getSheetAt(sheetNumber)
//            TODO("DB Exception Handling")
//            TODO("Duplicated Checker")
//            if(duplicatedSheet(topics,sheet))
            //Insert data to DB
            createTopic(sheet, lastTopicId + sheetNumber, questionFile.filePath)
            questionService.createQuestions(sheet, lastTopicId + sheetNumber)

            //Get date from DB
            val topicQuestionEntity = db.topicDao().getTopicById(lastTopicId + sheetNumber)
            addedTopicQuestionsEntities.add(topicQuestionEntity)
        }
        return addedTopicQuestionsEntities.toSet()
    }

//    private fun duplicatedSheet(topicQuestionsEntities: List<TopicQuestionsEntity>,sheet: Sheet): Boolean {
//
//
//                return true
//
//    }

    private fun createTopic(sheet: Sheet, topicStartNum: Int, filePath: String): Unit {
        val topicEntity = TopicEntity(topicStartNum, sheet.sheetName, filePath)

        db.topicDao().insertTopic(topicEntity)

        if (logger.isTraceEnabled) {
            logger.trace("Created Topic")
            logger.trace("topic -> name: {}, filename: {}", topicEntity.name, topicEntity.filePath)
        }

    }

    fun getTopics(): List<TopicQuestionsEntity>{
        return db.topicDao().getTopics()
    }

}
