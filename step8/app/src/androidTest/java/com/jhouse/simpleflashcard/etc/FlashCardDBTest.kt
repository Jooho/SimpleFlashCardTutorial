package com.jhouse.simpleflashcard.etc

import androidx.test.ext.junit.runners.AndroidJUnit4

//import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashCardDBTest : RootTest() {
//
//
//    @Test
//    fun getTopicQuestionsByName() {
//        // Test Data Setup
//        // Insert quiz.xlsx data
//        topicManager.addTopicsFromQuestionFile(
//            context,
//            QuestionFile(assetManager.open(testDateFile), testDateFilePath)
//        )
//
//        // Test Scenario(When)
//        val testQuestion = db.topicDao().getTopicQuestionsByName("TEST")
//
//        // Expected result (assert)
//        assertEquals(testQuestion.topic.name, "TEST")
//        assertEquals(testQuestion.topic.filePath, testDateFilePath)
//        assertEquals(testQuestion.questions.size, 4)
//        assertEquals(testQuestion.questions.elementAt(0).desc, "Q1")
//
//    }
//
//    @Test
//    fun createQuestionTestByAddTopicsFromQuestionFile() {
//        // Test Data Setup
//        var expectedQuestions = mutableSetOf<Question>()
//        expectedQuestions.add(Question(1, 1, "양/활동/불/화성", "백양자리"))
//        expectedQuestions.add(Question(2, 1, "양기의 에너지가 승하기 시작", "백양자리"))
//        expectedQuestions.add(Question(3, 1, "선두에 이끄는 성향. 개척정신이 강함", "백양자리/화성/불/활동"))
//        expectedQuestions.add(Question(143, 1, "Bad: 우유부단, 중독성, 속임수, 무절제", "물고기자리"))
//        expectedQuestions.add(Question(4, 1, "Q4", "A4/A5"))
//        val expectedTopics = mutableListOf<Topic>()
//        expectedTopics.add(Topic(1, "별자리", expectedQuestions.toSet(), "noFile"))
//
//        // Test Scenario(When)
//        var questionFile =
//            QuestionFile(assetManager.open(testDateFile), testDateFilePath)
//        // Load topic/questions via topicManager
//        topicManager.addTopicsFromQuestionFile(context, questionFile)
//
//        // Select QuestionData from DB directly
//        var cursor = sqlExecutor.select("Question")
//        // Select Question Data from DB via Room
//        var questions = db.questionDao().getQuestions()
//
//
//        // Expected result (assert)
//        cursor.moveToFirst()
//
//        assertEquals(
//            expectedTopics[0].questions.elementAt(0).desc,
//            cursor.getString(cursor.getColumnIndex("desc"))
//        )
//        assertEquals(
//            147,
//            cursor.count
//        )
//        println(questions.size)
//        assertIterableEquals(
//            expectedTopics[0].questions.elementAt(2).answers,
//            Question(questions.elementAt(2)).answers
//        );
//
//        assertEquals(
//            expectedTopics[0].questions.last().id,
//            questions.last().id
//        )
//
//        assertEquals(
//            expectedTopics[0].questions.last().desc,
//            questions.last().desc
//        )
//        assertIterableEquals(
//            expectedTopics[0].questions.last().answers,
//            Question(questions.last()).answers
//        )
//
//
//    }
//
//    @Test
//    fun createTopicTestByAddTopicsFromQuestionFile() {
//        // Test Data Setup
//        val expectedTopic = Topic(
//            2,
//            "TEST",
//            setOf(
//                Question(1, 2, "Q1", "A1"),
//                Question(2, 2, "Q2", "A2"),
//                Question(3, 2, "Q3", "A3"),
//                Question(4, 2, "Q4", "A4/A5")
//            )
//        )
//
//        // Test Scenario(When)
//        var questionFile =
//            QuestionFile(assetManager.open(testDateFile), testDateFilePath)
//        topicManager.addTopicsFromQuestionFile(context, questionFile)
//
//        // Select QuestionData from DB directly
//        var cursor = sqlExecutor.select("Question")
//        // Select Question Data from DB via Room
//        var actualTopic = db.topicDao().getTopics()
////        var actualQuestions = db.questionDao().getQuestionsByTopic("TEST")
//        var actualTopicQuestions = db.topicDao().getTopicQuestionsByName(actualTopic.last().name)
////        var actualQuestions = db.questionDao().getQuestions()
////
//
//        cursor.moveToLast()
//        println(cursor.getString(cursor.getColumnIndex("desc")))
//        // Expected result (assert)
//        assertEquals(expectedTopic.name, actualTopic.last().name)
//        assertEquals(expectedTopic.questions.size, actualTopicQuestions.questions.size)
//        assertEquals(
//            expectedTopic.questions.last().desc,
//            actualTopicQuestions.questions.last().desc
//        )
//
//
//    }
//
//
//
////    //FCC stand for FlashCard Config
////    @Test
////    fun getQuestionsWithConfigByFCService() {
////        // Test Data Setup
////        val expectedQuestionSize = 10
////
////        // Test Scenario(When)
////        var questionFile =
////            QuestionFile(context.assets.open("quiz.xlsx"), "quiz.xlsx")
////        // Load topic/questions via topicManager
////        topicManager.addTopicsFromQuestionFile(context, questionFile)
////        val questions = flashCardService.getQuestionsWithFCConfig("별자리", "default")
////
////        // Expected result (assert)
////        assertEquals(expectedQuestionSize, questions.size)
////    }
//
//    @Test
//    fun addPlayerByPlayerService() {
//        // Test Data Setup
//
//        val expectedFlashCardConfig= FlashCardConfig(QuestionType.CHOICES_4, 10, 11, true)
//        val expectedPlayer = Player(2, "TEST",expectedFlashCardConfig)
//        // Test Scenario(When)
//        playerService.addPlayer(expectedPlayer)
//
//        // Expected result (assert)
//        var cursor = sqlExecutor.select("Player")
//        cursor.moveToLast()
//
//        assertEquals(expectedPlayer.id, cursor.getInt(cursor.getColumnIndex("id")))
//        assertEquals(expectedPlayer.name, cursor.getString(cursor.getColumnIndex("name")))
//    }
//
//
//    @Test
//    fun getResultsWithResultGroupIdByHistoryService() {
//        // Test Data Setup
//
//        // Test Scenario(When)
//
//        // Expected result (assert)
//    }
//
//    @Test
//    fun getConfigWithPlayerByPlayerService() {
//        // Test Data Setup
////        val expectedConfig = ConfigEntity(1, 1, QuestionType.CHOICES_4, 10, 10, true)
//
//        // Test Scenario(When)
//
//
//        // Expected result (assert)
//    }
//

}

