package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ResultService private constructor(context: Context) {
    private val logger: Logger = LoggerFactory.getLogger(ResultService::class.java)
    companion object : SingletonHolder<ResultService, Context>(::ResultService)
    private var db: FlashCardDB = FlashCardDB.getInstance(context)

    fun addResults(results: List<ResultEntity>) {
        db.resultDao().addResults(results)
    }


}