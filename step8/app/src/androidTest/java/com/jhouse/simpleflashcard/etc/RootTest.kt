package com.jhouse.simpleflashcard.etc

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.SQLExecutor
import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerAndConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.player.PlayerManager
import com.jhouse.simpleflashcard.question.QuestionType
import com.jhouse.simpleflashcard.topic.TopicManager
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class RootTest {
    lateinit var db: FlashCardDB
    val logger: Logger = LoggerFactory.getLogger(RootTest::class.java)
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    var sqlExecutor = SQLExecutor.getInstance(context, "FlashCardDB5", 1)
    val assetManager = context.assets
    private val topicManager = TopicManager.getInstance(context)
    val testDateFile = "quiz.xlsx"
    val testDateFilePath = "/assets/quiz.xlsx"

    @Before
    fun setup() {
        // In order to use android poi, set system property
        //TODO("Need to find the way to use system.properties")
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLInputFactory",
            "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLOutputFactory",
            "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLEventFactory",
            "com.fasterxml.aalto.stax.EventFactoryImpl"
        );

        // Initialize DB
        db = FlashCardDB.getInstance(context)

        //Clean up all data
        sqlExecutor.deleteAll("Topic")
        sqlExecutor.deleteAll("Question")
        sqlExecutor.deleteAll("Player")
        PlayerManager.getInstance(context).clearPlayers()
        sqlExecutor.deleteAll("Config")
        sqlExecutor.deleteAll("Result")
        sqlExecutor.deleteAll("PlayerResult")
        // Don't use auto_increment
//        sqlExecutor.resetPointer("Topic")

        // Clear topic Set
        topicManager.clearTopics()

        // Insert test data
        // player is the root constraint to have data
        db.playerDao().insertPlayer(PlayerEntity(1, "default"))
        db.configDao().insertConfig(ConfigEntity(1, QuestionType.CHOICES_4, 10, 10, true))
        PlayerManager.getInstance(context).addPlayer(db.playerDao().getPlayers()[0])

//        //Insert quiz.xlsx data
//        val assetManager = context.assets
//        if (db.topicDao().getAll().isEmpty()) {
//            topicManager.addTopicsFromQuestionFile(
//                context,
//                QuestionFile(assetManager.open("quiz.xlsx"), "quiz.xlsx")
//            )
//        }
    }


    @After
    fun teardown() {
        //
        sqlExecutor.closeDb()
    }
}