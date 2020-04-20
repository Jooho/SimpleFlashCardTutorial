package com.jhouse.simpleflashcard.result

import com.jhouse.simpleflashcard.db.result.ResultGroupEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ResultGroupFactory {
    private val logger: Logger = LoggerFactory.getLogger(ResultGroupFactory::class.java)

    fun createResultGroup(resultGroupEntity: ResultGroupEntity): ResultGroup {
        var results = mutableListOf<Result>()
        resultGroupEntity.results.forEach {
            results.add(Result(it))
        }

        return ResultGroup(
            resultGroupEntity.playerResult.id,
            resultGroupEntity.playerResult.playerId,
            resultGroupEntity.playerResult.topicId,
            resultGroupEntity.playerResult.date,
            results
        )

    }


}