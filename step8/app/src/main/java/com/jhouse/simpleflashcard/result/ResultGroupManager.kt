package com.jhouse.simpleflashcard.result

import android.content.Context
import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import com.jhouse.simpleflashcard.svc.ResultGroupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ResultGroupManager private constructor(context: Context) {
    private val logger: Logger = LoggerFactory.getLogger(ResultGroupManager::class.java)

    companion object : SingletonHolder<ResultGroupManager, Context>(::ResultGroupManager)

    private var resultGroup = mutableListOf<ResultGroup>()

    private val resultGroupService = ResultGroupService.getInstance(context)


    fun initResultGroupManager() {
        if (resultGroup.isNotEmpty()) resultGroup.clear()

        val history = resultGroupService.getHistory()

        history.forEach {
            resultGroup.add(ResultGroupFactory.createResultGroup(it))
        }
    }

    fun addResultGroup(playerName: String, topicName: String, results: List<Result>) {
        val resultEntities = mutableListOf<ResultEntity>()
        results.forEach{resultEntities.add(it.resultEntity)}

        var resultGroupEntity = resultGroupService.addResultGroup(playerName, topicName, resultEntities)

        resultGroup.add(ResultGroupFactory.createResultGroup(resultGroupEntity))
    }

    fun getResultGroups(): List<ResultGroup> {
        return resultGroup.toList()
    }


}