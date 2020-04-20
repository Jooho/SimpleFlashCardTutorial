package com.jhouse.simpleflashcard

import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.nio.file.Paths


object TestUtil {
    fun getTestResourceFilePathString(fileName: String): String {
        val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
        return Paths.get(projectDirAbsolutePath, "/src/test/resources/$fileName").toString()
    }


    fun getFirstSheet(testResourceFileName: String): Sheet {
        val inputStream = FileInputStream(
            getTestResourceFilePathString(
                testResourceFileName
            )
        )
        return WorkbookFactory.create(inputStream).getSheetAt(0)

    }
}