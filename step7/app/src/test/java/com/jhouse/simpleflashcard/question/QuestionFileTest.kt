package com.jhouse.simpleflashcard.question

import com.jhouse.simpleflashcard.TestUtil
import com.jhouse.simpleflashcard.topic.Topic
import org.apache.poi.ss.usermodel.Workbook
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Question File")
class QuestionFileTest {


    @Nested
    @DisplayName("질문 엑셀파일")
    inner class `XLSFile`() {

        @Test
        @DisplayName("엑셀 파일 내용비교")
        fun `Load xls file`() {
            // Test Data Setup
            val testDateFile = "test/quiz.xlsx"
            val expectedXlws = TestUtil.getFirstSheet(testDateFile)
            // println(expectedXlws.getRow(1).getCell(0))

            // Test Scenario(When)
            var questionFile =
                QuestionFile("/home/jooho/dev/Private_Git/flashcard-api/src/test/resources/quiz.xlsx")

            val actualXlwb: Workbook = questionFile.loadXLSFile()
            val actualXlws = actualXlwb.getSheetAt(0)

            // Expected result (assert)
            assertEquals(
                expectedXlws.getRow(1).getCell(0).toString(),
                actualXlws.getRow(1).getCell(0).toString(),
                "Question Column does not exist"
            )
            assertEquals(
                expectedXlws.getRow(1).getCell(1).toString(),
                actualXlws.getRow(1).getCell(1).toString(),
                "Answer Column does not exist"
            )
        }
    }


    @Nested
    @DisplayName("Topics 과 Question/Answer 만들기")
    inner class `Topics`() {

        @Test
        fun `Create Topic after creating questions`() {
            // Test Data Setup
            var expectedQuestions = mutableSetOf<Question>()
            expectedQuestions.add(Question(1, "양/활동/불/화성", "백양자리"))
            expectedQuestions.add(Question(2, "양기의 에너지가 승하기 시작", "백양자리"))
            expectedQuestions.add(Question(3, "선두에 이끄는 성향. 개척정신이 강함", "백양자리/화성/불/활동"))
            expectedQuestions.add(Question(143, "Bad: 우유부단, 중독성, 속임수, 무절제", "물고기자리"))
            val expectedTopics = mutableListOf<Topic>()
            expectedTopics.add(Topic("별자리", expectedQuestions.toSet(), "noFile"))
            // Test Scenario(When)
            var questionFile =
                QuestionFile("/home/jooho/dev/Private_Git/flashcard-api/src/test/resources/quiz.xlsx")
            val actualTopics: List<Topic> = questionFile.createTopics(questionFile.loadXLSFile())

            // Expected result (assert)
            assertEquals(expectedTopics[0].name, actualTopics[0].name)
            assertEquals(
                expectedTopics[0].questions.elementAt(0).desc,
                actualTopics[0].questions.elementAt(0).desc
            )
            assertIterableEquals(
                expectedTopics[0].questions.elementAt(2).answers,
                actualTopics[0].questions.elementAt(2).answers
            );
            assertEquals(
                expectedTopics[0].questions.last().desc,
                actualTopics[0].questions.last().desc
            )
            assertIterableEquals(
                expectedTopics[0].questions.last().answers,
                actualTopics[0].questions.last().answers
            )

        }


        @Test
        @Disabled()
        @DisplayName("중복된 질문은 나중것을 넣지 않는다.")
        fun `Check duplicated question`() {
            // Test Data Setup

            // Test Scenario(When)

            // Expected result (assert)
        }


    }
}
