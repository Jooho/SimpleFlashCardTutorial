package com.jhouse.simpleflashcard.etc

import android.content.Context
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.InputStream
import java.nio.file.Paths


object AndroidTestUtil {

    fun getFirstSheet(testResourceFileName: InputStream, sheetName:String, context: Context): Sheet {

        return WorkbookFactory.create(testResourceFileName).getSheet(sheetName)

    }
}