package com.jhouse.simpleflashcard.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DBHelper(context: Context, databaseName: String, dbVersion: Int) : SQLiteOpenHelper(context, databaseName, null, dbVersion) {
    private val logger: Logger = LoggerFactory.getLogger(DBHelper::class.java)


    override fun onCreate(db: SQLiteDatabase?) {}
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


}