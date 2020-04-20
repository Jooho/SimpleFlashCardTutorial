package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FlashCardService {
    private val logger: Logger = LoggerFactory.getLogger(FlashCardService::class.java)

    private lateinit var db: FlashCardDB
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    companion object {
        private var instance: FlashCardService? = null

        fun getInstance(context: Context): FlashCardService {

            return instance ?: synchronized(this) {
                FlashCardService(context)
            }
        }
    }


    fun setDb(): Unit {
        db = FlashCardDB.getInstance(context)
    }

//    fun addResults(results: List<ResultEntity>) {
//        setDb()
//        db.resultDao().addResults(results)
//    }
//
//    fun addResultGroup(playerName: String, topicName: String, result: List<ResultEntity>) {
//        setDb()
//        val player = db.playerDao().getPlayerByName(playerName)
//        val topic = db.topicDao().getTopicByName(topicName)
//        var resultGroup = db.resultGroupDao().getAll()
//
//        val resultGroupEntity =
//            ResultGroupEntity(
//                resultGroup.size + 1,
//                player.id,
//                topic.id,
//                getFormatedDate()
//            )
//
//        db.resultGroupDao().addResultGroup(resultGroupEntity)
//        resultGroup = db.resultGroupDao().getAll()
//
//        db.resultDao().addResults(result)
//
//    }
//
////    fun getQuestionsWithFCConfig(topicName: String, playerName: String): Set<Question> {
////        setDb()
////        val topic: Topic = topicManager.getTopicByName(topicName)
////        val playerConfigEntity = db.playerDao().getPlayerConfigByName(playerName)
////
////        val flashCardQuestions = mutableSetOf<Question>()
////        for (i in 0 until playerConfigEntity.config.questionCount) {
////            var tempQuestion = topic.questions.random()
////            while (flashCardQuestions.contains(tempQuestion)) {
////                tempQuestion = topic.questions.random()
////            }
////            flashCardQuestions.add(tempQuestion)
////        }
////
////        return flashCardQuestions
////    }
//
//    private fun getFormatedDate(): String {
//        var formatter = SimpleDateFormat("yyyyMMDDHHmm")
//        var date = formatter.format(Date())
//        return date
//    }


}