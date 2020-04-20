package com.jhouse.simpleflashcard.db

import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionType
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class PlayerDaoTest : RootTest() {


    @Test
    fun testInsertPlayer() {
        // Test Data Setup
        val expectedPlayer = PlayerEntity(2, "TEST")

        // Test Scenario(When)
        db.playerDao().insertPlayer(expectedPlayer)

        // Expected result (assert)
        var cursor = sqlExecutor.select("Player")
        cursor.moveToLast()
        Assert.assertEquals(expectedPlayer.id, cursor.getInt(cursor.getColumnIndex("id")))
        Assert.assertEquals(expectedPlayer.name, cursor.getString(cursor.getColumnIndex("name")))
    }

    @Test
    fun testGetPlayers() {
        // Test Data Setup
        val expectedPlayerEntity=PlayerEntity(2, "test")
        val expectedConfigEntity= ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val expectedPlayerCount = 2

        db.playerDao().insertPlayer(expectedPlayerEntity)
        db.configDao().insertConfig(expectedConfigEntity)

        // Test Scenario(When)
        val actualPlayer = db.playerDao().getPlayers()

        // Expected result (assert)
        assertEquals(expectedPlayerCount,actualPlayer.size)
        assertEquals(expectedPlayerEntity.name,actualPlayer.last().player.name)
    }

    @Test
    fun testGetPlayersByName() {
        // Test Data Setup
        val expectedPlayerEntity=PlayerEntity(2, "test")
        val expectedConfigEntity= ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)

        db.playerDao().insertPlayer(expectedPlayerEntity)
        db.configDao().insertConfig(expectedConfigEntity)

        // Test Scenario(When)
        val actualPlayer = db.playerDao().getPlayerByName("test")

        // Expected result (assert)
        assertEquals(expectedPlayerEntity.id,actualPlayer.player.id)
        assertEquals(expectedPlayerEntity.name,actualPlayer.player.name)

        assertEquals(expectedConfigEntity.questionType,actualPlayer.config.questionType)
        assertEquals(expectedConfigEntity.questionCount,actualPlayer.config.questionCount)
        assertEquals(expectedConfigEntity.timeLimitSecs,actualPlayer.config.timeLimitSecs)
        assertEquals(expectedConfigEntity.saveResult,actualPlayer.config.saveResult)
    }

    @Test
    fun testUpdatePlayer() {
        // Test Data Setup
        val oldPlayerEntity=PlayerEntity(2, "test")
        val newPlayerEntity=PlayerEntity(2, "new_player")
        val oldConfigEntity= ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        db.playerDao().insertPlayer(oldPlayerEntity)
        db.configDao().insertConfig(oldConfigEntity)

        // Test Scenario(When)
        db.playerDao().updatePlayer(newPlayerEntity)
        val actualPlayer=db.playerDao().getPlayerByName("new_player")

        // Expected result (assert)
        assertNotNull(actualPlayer)
        assertEquals(newPlayerEntity.name,actualPlayer.player.name)

    }

    @Test
    fun testDeletePlayer(): Unit {
        // Test Data Setup
        val expectedPlayerEntity=PlayerEntity(2, "test")
        val expectedConfigEntity= ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val expectedPlayerCountAfterAddPlayer =2
        val expectedPlayerCountAfterDelPlayer =1
        db.playerDao().insertPlayer(expectedPlayerEntity)
        db.configDao().insertConfig(expectedConfigEntity)
        var addedPlayer = db.playerDao().getPlayers()
        assertEquals(expectedPlayerCountAfterAddPlayer,addedPlayer.size)

        // Test Scenario(When)
        db.playerDao().deletePlayer(expectedPlayerEntity)

        // Expected result (assert)
        val afterDeletePlayer = db.playerDao().getPlayers()
        assertEquals(expectedPlayerCountAfterDelPlayer,afterDeletePlayer.size)

        val cursor =sqlExecutor.selectByWhere("Config","Config.player_id =2")
        assertEquals(0,cursor.count)


    }
}