package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.result.ResultGroupEntity
import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.result.Result
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*

class ResultGroupService private constructor(context: Context) {

    private val logger: Logger = LoggerFactory.getLogger(ResultGroupService::class.java)

    companion object : SingletonHolder<ResultGroupService, Context>(::ResultGroupService)

    private var db: FlashCardDB = FlashCardDB.getInstance(context)
    private val resultService = ResultService.getInstance(context)

    fun getHistory(): List<ResultGroupEntity> {
        return db.resultGroupDao().getResultGroups()
    }

    fun addResultGroup(playerName: String, topicName: String, result: List<ResultEntity>):ResultGroupEntity {
        val playerAndConfigEntity = db.playerDao().getPlayerByName(playerName)
        val topicQuestionsEntity = db.topicDao().getTopicByName(topicName)
        var resultGroupLastId = db.resultGroupDao().getAll().size

        val resultGroupEntity =
            PlayerResultEntity(
                resultGroupLastId + 1,
                playerAndConfigEntity.player.id,
                topicQuestionsEntity.topic.id,
                getFormatedDate()
            )

        db.resultGroupDao().addResultGroup(resultGroupEntity)
        resultService.addResults(result)

        return db.resultGroupDao().getHistoryById(resultGroupEntity.id)
    }

//In case
//    fun addResultGroup(resultGroup: ResultGroup) {
//
//        var resultGroupLastId = db.resultGroupDao().getAll().size
//
//        val resultGroupEntity =
//            ResultGroupEntity(
//                resultGroupLastId + 1,
//                resultGroup.playerId,
//                resultGroup.topicId,
//                getFormatedDate()
//            )
//
//        db.resultGroupDao().addResultGroup(resultGroupEntity)
//
//        var resultEntities= mutableListOf<ResultEntity>()
//        resultGroup.results.forEach{
//            resultEntities.add(convertResultToResultEntity(it))
//        }
//        db.resultDao().addResults(resultEntities)
//
//    }

//    TODO()
//    fun getQuestionsWithFCConfig(topicName: String, playerName: String): Set<Question> {
//        setDb()
//        val topic: Topic = topicManager.getTopicByName(topicName)
//        val playerConfigEntity = db.playerDao().getPlayerConfigByName(playerName)
//
//        val flashCardQuestions = mutableSetOf<Question>()
//        for (i in 0 until playerConfigEntity.config.questionCount) {
//            var tempQuestion = topic.questions.random()
//            while (flashCardQuestions.contains(tempQuestion)) {
//                tempQuestion = topic.questions.random()
//            }
//            flashCardQuestions.add(tempQuestion)
//        }
//
//        return flashCardQuestions
//    }



    private fun convertResultToResultEntity(result: Result): ResultEntity {
        return result.resultEntity
    }


    private fun getFormatedDate(): String {
        var formatter = SimpleDateFormat("yyyyMMDDHHmm")
        var date = formatter.format(Date())
        return date
    }


}