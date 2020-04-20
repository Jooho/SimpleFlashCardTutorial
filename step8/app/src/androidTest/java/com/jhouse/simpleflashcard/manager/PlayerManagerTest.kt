package com.jhouse.simpleflashcard.manager

import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerAndConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.player.PlayerFactory
import com.jhouse.simpleflashcard.player.PlayerManager
import com.jhouse.simpleflashcard.question.QuestionType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class PlayerManagerTest : RootTest() {

    private val playerManager = PlayerManager.getInstance(context)

    @Test
    fun testInitPlayerManagerAndGetPlayers() {
        // Test Data Setup
        val expectedPlayerEntity = PlayerEntity(2, "TEST")
        val expectedConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val expectedPlayerCount = 2

        db.playerDao().insertPlayer(expectedPlayerEntity)
        db.configDao().insertConfig(expectedConfigEntity)

        // Test Data Verify
        val actualPlayerConfigEntities = db.playerDao().getPlayers()
        assertEquals(expectedPlayerCount, actualPlayerConfigEntities.size)
        assertEquals(expectedPlayerEntity.name, actualPlayerConfigEntities.last().player.name)
        assertEquals(
            expectedConfigEntity.questionCount,
            actualPlayerConfigEntities.last().config.questionCount
        )

        // Test Scenario(When)
        playerManager.initPlayerManager()
        val actualPlayers = playerManager.getPlayers()

        // Expected result (assert)
        assertEquals(expectedPlayerCount, actualPlayers.size)
        assertEquals(expectedPlayerEntity.name, actualPlayers.last().name)
        assertEquals(expectedConfigEntity.questionCount, actualPlayers.last().config.questionCount)

    }


    @Test
    fun testGetPlayerByName() {
        // Test Data Setup
        val expectedPlayerEntity = PlayerEntity(2, "TEST")
        val expectedConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val expectedPlayerCount = 2

        db.playerDao().insertPlayer(expectedPlayerEntity)
        db.configDao().insertConfig(expectedConfigEntity)

        // Test Data Verify
        val actualPlayerConfigEntities = db.playerDao().getPlayers()
        assertEquals(expectedPlayerCount, actualPlayerConfigEntities.size)
        assertEquals(expectedPlayerEntity.name, actualPlayerConfigEntities.last().player.name)
        assertEquals(
            expectedConfigEntity.questionCount,
            actualPlayerConfigEntities.last().config.questionCount
        )


        // Test Scenario(When)
        playerManager.initPlayerManager()
        val actualPlayer = playerManager.getPlayerByName("TEST")

        // Expected result (assert)
        assertEquals(expectedPlayerEntity.name, actualPlayer.name)
        assertEquals(expectedConfigEntity.questionCount, actualPlayer.config.questionCount)

    }

    @Test
    fun testAddPlayer() {
        // Test Data Setup
        val expectedPlayerEntity = PlayerEntity(2, "TEST")
        val expectedConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 10, true)
        val expectedPlayerCount = 2

        // Test Scenario(When)
        playerManager.addPlayer("TEST")

        // Expected result (assert)
        val actualPlayers = playerManager.getPlayers()
        val actualPlayerConfigEntities = db.playerDao().getPlayers()
        //Topics
        assertEquals(expectedPlayerCount, actualPlayers.size)
        assertEquals(expectedPlayerEntity.name, actualPlayers.last().name)
        assertEquals(expectedConfigEntity.questionCount, actualPlayers.last().config.questionCount)
        //DB
        assertEquals(expectedPlayerEntity.name, actualPlayerConfigEntities.last().player.name)
        assertEquals(
            expectedConfigEntity.questionCount,
            actualPlayerConfigEntities.last().config.questionCount
        )
    }

    @Test
    fun testUpdatePlayer() {
        // Test Data Setup
        val oldPlayerEntity = PlayerEntity(2, "TEST")
        val newPlayerEntity = PlayerEntity(2, "new_player")
        val oldConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 1, true)
        val newConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        db.playerDao().insertPlayer(oldPlayerEntity)
        db.configDao().insertConfig(oldConfigEntity)
        PlayerManager.getInstance(context).addPlayer(db.playerDao().getPlayers().last())

        // Test Scenario(When) - new player update
        playerManager.updatePlayer(PlayerFactory.createPlayer(PlayerAndConfigEntity(oldPlayerEntity, oldConfigEntity)),PlayerFactory.createPlayer(PlayerAndConfigEntity(newPlayerEntity, oldConfigEntity)))
        // Expected result (assert) - new player update - DB
        var actualPlayerAndConfigEntity = db.playerDao().getPlayerByName(newPlayerEntity.name)
        assertNotNull(actualPlayerAndConfigEntity)
        assertEquals(newPlayerEntity.name, actualPlayerAndConfigEntity.player.name)

        // Expected result (assert) - new config update - Object
        var actualPlayer = playerManager.getPlayerByName(newPlayerEntity.name)
        assertEquals(newPlayerEntity.name, actualPlayer.name)


        // Test Scenario(When) - new config update
        playerManager.updatePlayer(PlayerFactory.createPlayer(PlayerAndConfigEntity(newPlayerEntity, oldConfigEntity)),PlayerFactory.createPlayer(PlayerAndConfigEntity(newPlayerEntity, newConfigEntity)))
        // Expected result (assert) - new config update - DB
        actualPlayerAndConfigEntity = db.playerDao().getPlayerByName(newPlayerEntity.name)
        assertNotNull(actualPlayerAndConfigEntity)
        assertEquals(newConfigEntity.questionCount, actualPlayerAndConfigEntity.config.questionCount)

        // Expected result (assert) - new config update - Object
        actualPlayer = playerManager.getPlayerByName(newPlayerEntity.name)
        assertEquals(newPlayerEntity.name, actualPlayer.name)


        // Test Scenario(When) - both player/config update
        playerManager.updatePlayer(PlayerFactory.createPlayer(PlayerAndConfigEntity(newPlayerEntity, newConfigEntity)),PlayerFactory.createPlayer(PlayerAndConfigEntity(oldPlayerEntity, oldConfigEntity)))
        // Expected result (assert) - both player/config update - DB
        actualPlayerAndConfigEntity = db.playerDao().getPlayerByName(oldPlayerEntity.name)
        assertNotNull(actualPlayerAndConfigEntity)
        assertEquals(oldConfigEntity.questionCount, actualPlayerAndConfigEntity.config.questionCount)

        // Expected result (assert) - both player/config update - Object
        actualPlayer = playerManager.getPlayerByName(oldPlayerEntity.name)
        assertEquals(oldPlayerEntity.name, actualPlayer.name)
    }


}