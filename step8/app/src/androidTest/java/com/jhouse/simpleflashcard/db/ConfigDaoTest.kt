package com.jhouse.simpleflashcard.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfigDaoTest : RootTest() {

    @Test
    fun testAddConfig() {
        // Test Data Setup
        val expectedPlayerEntity = PlayerEntity(2, "test")
        val expectedConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)

        //pre-condition
        db.playerDao().insertPlayer(expectedPlayerEntity)


        // Test Scenario(When)
        db.configDao().insertConfig(expectedConfigEntity)

        val actualConfig = db.configDao().getConfigByPlayerId(expectedPlayerEntity.id)

        // Expected result (assert)
        assertNotNull(actualConfig)

        assertEquals(expectedConfigEntity.timeLimitSecs, actualConfig.timeLimitSecs)
        assertEquals(expectedConfigEntity.questionType, actualConfig.questionType)

    }

    @Test
    fun testUpdateConfig() {

        // Test Data Setup
        val oldPlayerEntity = PlayerEntity(2, "test")
        val oldConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val newConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 100, 100, true)
        db.playerDao().insertPlayer(oldPlayerEntity)
        db.configDao().insertConfig(oldConfigEntity)

        // Test Scenario(When)
        db.configDao().updateConfig(newConfigEntity)


        // Expected result (assert)
        val actualConfig = db.configDao().getConfigByPlayerId(oldPlayerEntity.id)

        assertNotNull(actualConfig)

        assertEquals(newConfigEntity.timeLimitSecs, actualConfig.timeLimitSecs)
        assertEquals(newConfigEntity.questionType, actualConfig.questionType)
    }



}