package com.jhouse.simpleflashcard.svc

import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerAndConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionType
import org.junit.Assert
import org.junit.Test

class PlayerServiceTest : RootTest() {
    private val playerService = PlayerService.getInstance(context)

    @Test
    fun testAddPlayerEntity() {
        // Test Data Setup
        val expectedPlayer = PlayerEntity(2, "TEST")

        // Test Scenario(When)
        playerService.addPlayer(expectedPlayer)

        // Expected result (assert)
        var cursor = sqlExecutor.select("Player")
        cursor.moveToLast()
        Assert.assertEquals(expectedPlayer.id, cursor.getInt(cursor.getColumnIndex("id")))
        Assert.assertEquals(expectedPlayer.name, cursor.getString(cursor.getColumnIndex("name")))
    }

    @Test
    fun testAddPlayerWithName() {
        // Test Data Setup
        val expectedPlayer = PlayerEntity(2, "TEST")

        // Test Scenario(When)
        playerService.addPlayer(expectedPlayer.name)

        // Expected result (assert)
        var cursor = sqlExecutor.select("Player")
        cursor.moveToLast()
        Assert.assertEquals(expectedPlayer.id, cursor.getInt(cursor.getColumnIndex("id")))
        Assert.assertEquals(expectedPlayer.name, cursor.getString(cursor.getColumnIndex("name")))
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
        playerService.updatePlayer(PlayerAndConfigEntity( newPlayerEntity,oldConfigEntity))
        val actualPlayer=db.playerDao().getPlayerByName("new_player")

        // Expected result (assert)
        Assert.assertNotNull(actualPlayer)
        Assert.assertEquals(newPlayerEntity.name, actualPlayer.player.name)
    }

    @Test
    fun testGetPlayerAndConfigEntities(){
        // Test Data Setup
        val expectedPlayerEntity=PlayerEntity(2, "test")
        val expectedConfigEntity= ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val expectedPlayerCount = 2

        db.playerDao().insertPlayer(expectedPlayerEntity)
        db.configDao().insertConfig(expectedConfigEntity)

        // Test Scenario(When)
        val actualPlayer = playerService.getPlayerAndConfigEntities()

        // Expected result (assert)
        Assert.assertEquals(expectedPlayerCount, actualPlayer.size)
    }

}