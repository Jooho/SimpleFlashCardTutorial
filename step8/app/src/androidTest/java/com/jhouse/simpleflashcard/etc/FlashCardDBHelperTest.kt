package com.jhouse.simpleflashcard.etc

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jhouse.simpleflashcard.db.SQLExecutor
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.topic.TopicDao
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FlashCardDBHelperTest {
    val TABLE = "QUESTION"

    private lateinit var topicDao: TopicDao
    private lateinit var db: FlashCardDB
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private var sqlExecutor = SQLExecutor.getInstance(context, "FlashCardDB", 1)
    @Before
    fun setup() {
        // val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = FlashCardDB.getInstance(context)
        topicDao = db.topicDao()

        db.topicDao().getTopics().forEach{

            db.topicDao().deleteTopic(it.topic)
        }

        //Delete sqlite_sequence for the table
        sqlExecutor.resetPointer("Topic")

        //Checking sequence is really cleared
        var topics = topicDao.getTopics()
        for (topic in topics) {
            System.out.println("TESTSSS_id:" + topic.topic.id)
        }

    }


    @Test
    fun loadT() {
    // Test Data Setup

    // Test Scenario(When)

    // Expected result (assert)
    }

//    @Test
//    fun getQuestionAnswer() {
//       db.topicDao()
//        db.topicDao().insertTopic(TopicEntity(null, "testTopic", "testFilePath"))
//        var topics = topicDao.getAll()
////        println(topics)
//        for (topic in topics) {
//
//            System.out.println("IN_TESTSSS(id):" + topic.id)
//            System.out.println("IN_TESTSSS(name):" + topic.name)
//            System.out.println("IN_TESTSSS(size):" + topics.size)
//
//        }
////            Log.i("test",topics.toString())
//        assertEquals("ta", topics)
//    }


}