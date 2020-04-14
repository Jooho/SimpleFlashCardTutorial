package com.jhouse.simpleflashcard.question

import com.jhouse.simpleflashcard.topic.Topic
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.concurrent.atomic.AtomicInteger

class QuestionFile(private val fileInputStream: InputStream,private val filePath:String) {

    constructor(filePath:String) :this(FileInputStream(filePath),filePath)

    var id = AtomicInteger()
    fun load(topics: MutableSet<Topic>): Boolean {
        var success = false
        val workBook = loadXLSFile()
        createTopics(workBook).forEach { topic ->
            topics.add(topic)
        }
        return success
    }

    fun loadXLSFile(): Workbook {
        return WorkbookFactory.create(fileInputStream)
    }

    fun createTopics(workbook: Workbook): List<Topic> {
        var topics = mutableListOf<Topic>()
        var sheet: Sheet
        for (sheetNumber in 0 until workbook.numberOfSheets.toInt()) {
            sheet = workbook.getSheetAt(sheetNumber)
            topics.add(Topic(sheet.sheetName, createQuestions(sheet), filePath))
        }

        return topics
    }
    private fun createQuestions(sheet: Sheet): Set<Question> {
        var questions = mutableSetOf<Question>()

        sheet.rowIterator().forEach {
            if (it.getCell(0).toString() !in setOf("Question", "")) {
                questions.add(Question(id.getAndIncrement(),it.getCell(0).toString(), it.getCell(1).toString()))
            }
        }

        return questions.toSet()
    }


}
