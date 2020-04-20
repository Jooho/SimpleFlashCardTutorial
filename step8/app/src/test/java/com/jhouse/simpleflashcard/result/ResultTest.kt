package com.jhouse.simpleflashcard.result


import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultTest {
    private val logger: Logger = LoggerFactory.getLogger(ResultTest::class.java)

    @Test
    fun `Time Format`() {
        // Test Data Setup
        val expectedDate = "202004162321"
        val testRawDate = "2020-04-16T23:21:33.336"
        // Test Scenario(When)
        var formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm")
        var actualDate = formatter.format(LocalDateTime.parse(testRawDate))

        // Expected result (assert)
        assertEquals(expectedDate, actualDate)
    }

}