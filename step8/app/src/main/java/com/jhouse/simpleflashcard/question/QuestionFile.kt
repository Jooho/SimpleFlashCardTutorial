package com.jhouse.simpleflashcard.question

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.FileInputStream
import java.io.InputStream

class QuestionFile(private val fileInputStream: InputStream, val filePath: String) {

    private val logger: Logger = LoggerFactory.getLogger(QuestionFile::class.java)

    constructor(filePath: String) : this(FileInputStream(filePath), filePath)

    fun loadXLSFile(): Workbook {
        return WorkbookFactory.create(fileInputStream)
    }

}
