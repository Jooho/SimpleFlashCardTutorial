package com.jhouse.simpleflashcard.manager

import com.jhouse.simpleflashcard.db.result.ResultGroupEntity
import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.db.topic.TopicEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.result.Result
import com.jhouse.simpleflashcard.result.ResultGroup
import com.jhouse.simpleflashcard.result.ResultGroupFactory
import com.jhouse.simpleflashcard.result.ResultGroupManager
import com.jhouse.simpleflashcard.svc.ResultGroupService
import com.jhouse.simpleflashcard.svc.ResultService
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*


class ResultGroupManagerTest : RootTest() {
//

    private val resultGroupManager = ResultGroupManager.getInstance(context)
    private val resultGroupService = ResultGroupService.getInstance(context)
    private val resultService = ResultService.getInstance(context)

    @Test
    fun initResultGroupManager() {
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)
        val exepctedResultGroup: ResultGroup = ResultGroupFactory.createResultGroup(
            ResultGroupEntity(
                expectedResultGroupEntity,
                listOf(expectedResultEntity)
            )
        )

        // Add Topic (Pre requirement)
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)
        db.resultDao().addResults(listOf(expectedResultEntity))

        // Test Scenario(When)
        resultGroupManager.initResultGroupManager()

        // Expected result (assert)
        val actualResultGroups = resultGroupService.getHistory()
        assertEquals(exepctedResultGroup.id, actualResultGroups.first().playerResult.id)
        assertEquals(exepctedResultGroup.playerId, actualResultGroups.first().playerResult.playerId)
        assertEquals(exepctedResultGroup.topicId, actualResultGroups.first().playerResult.topicId)
        assertEquals(exepctedResultGroup.date, actualResultGroups.first().playerResult.date)
        assertEquals(exepctedResultGroup.results.size, actualResultGroups.first().results.size)
    }

    @Test
    fun addResultGroup() {

        // Test Data Setup
        val expectedDate = getFormatedDate()

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)
        val expectedResultGroup: ResultGroup = ResultGroupFactory.createResultGroup(
            ResultGroupEntity(
                expectedResultGroupEntity,
                listOf(expectedResultEntity)
            )
        )

        // Add Topic (Pre requirement)
        db.topicDao().insertTopic(expectedTopicEntity)

        // Test Scenario(When)
        resultGroupManager.addResultGroup("default", "별자리", listOf(Result(expectedResultEntity)))

        // Expected result (assert)
        val actualResultGroups = resultGroupService.getHistory()
        assertEquals(expectedResultGroup.id, actualResultGroups.first().playerResult.id)
        assertEquals(expectedResultGroup.playerId, actualResultGroups.first().playerResult.playerId)
        assertEquals(expectedResultGroup.topicId, actualResultGroups.first().playerResult.topicId)
        assertEquals(expectedResultGroup.date, actualResultGroups.first().playerResult.date)
        assertEquals(expectedResultGroup.results.size, actualResultGroups.first().results.size)
    }

    @Test
    fun getResultGroups() {
        // Test Data Setup
        val expectedDate = "202004162321"

        val expectedTopicEntity = TopicEntity(1, "별자리", "quiz.xlsx")
        var expectedResultGroupEntity = PlayerResultEntity(1, 1, 1, expectedDate)
        var expectedResultEntity = ResultEntity(1, 1, "백양자리", "백양자리", true)

        // Pre requirement
        db.topicDao().insertTopic(expectedTopicEntity)
        db.resultGroupDao().addResultGroup(expectedResultGroupEntity)
        db.resultDao().addResult(expectedResultEntity)
        resultGroupManager.initResultGroupManager()

        // Test Scenario(When)
        val actualResultGroups = resultGroupManager.getResultGroups()

        // Expected result (assert)
        assertEquals(expectedResultGroupEntity.id, actualResultGroups.first().id)
        assertEquals(expectedResultGroupEntity.playerId, actualResultGroups.first().playerId)
        assertEquals(expectedResultGroupEntity.topicId, actualResultGroups.first().topicId)
        assertEquals(expectedResultGroupEntity.date, actualResultGroups.first().date)
        assertEquals(expectedResultEntity.questionId, actualResultGroups.first().results.first().resultEntity.questionId)
    }


    private fun getFormatedDate(): String {
        var formatter = SimpleDateFormat("yyyyMMDDHHmm")
        var date = formatter.format(Date())
        return date
    }
}