package com.jhouse.simpleflashcard.svc

import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.etc.RootTest
import com.jhouse.simpleflashcard.question.QuestionType
import org.junit.Assert
import org.junit.Test

class ConfigServiceTest: RootTest() {
    private val configService = ConfigService.getInstance(context)
    @Test
    fun testAddConfig(){
        // Test Data Setup
        val expectedPlayerEntity = PlayerEntity(2, "test")
        val expectedConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)

        //pre-condition
        db.playerDao().insertPlayer(expectedPlayerEntity)


        // Test Scenario(When)
        configService.addConfig(expectedConfigEntity)

        val actualConfig = db.configDao().getConfigByPlayerId(expectedPlayerEntity.id)

        // Expected result (assert)
        Assert.assertNotNull(actualConfig)

        Assert.assertEquals(expectedConfigEntity.timeLimitSecs, actualConfig.timeLimitSecs)
        Assert.assertEquals(expectedConfigEntity.questionType, actualConfig.questionType)

    }

    @Test
    fun testUpdateConfig(){
        // Test Data Setup
        val oldPlayerEntity = PlayerEntity(2, "test")
        val oldConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 10, 11, true)
        val newConfigEntity = ConfigEntity(2, QuestionType.CHOICES_4, 100, 100, true)
        db.playerDao().insertPlayer(oldPlayerEntity)
        db.configDao().insertConfig(oldConfigEntity)

        // Test Scenario(When)
        configService.updatConfig(newConfigEntity)

        // Expected result (assert)
        val actualConfig = db.configDao().getConfigByPlayerId(oldPlayerEntity.id)

        Assert.assertNotNull(actualConfig)

        Assert.assertEquals(newConfigEntity.timeLimitSecs, actualConfig.timeLimitSecs)
        Assert.assertEquals(newConfigEntity.questionType, actualConfig.questionType)

    }

}