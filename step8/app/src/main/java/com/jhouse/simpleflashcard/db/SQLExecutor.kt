package com.jhouse.simpleflashcard.db

import android.content.Context
import android.database.Cursor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SQLExecutor(context: Context, dbName: String, dbVersion: Int) {
    private val logger: Logger = LoggerFactory.getLogger(SQLExecutor::class.java)
    private val nonRoomDb = DBHelper(context, dbName, dbVersion)

    companion object {

        private var instance: SQLExecutor? = null

        fun getInstance(context: Context, dbName: String, dbVersion: Int): SQLExecutor {
            return instance ?: synchronized(this) {
                instance ?: createSQLExecutor(context, dbName, dbVersion)
            }
        }

        private fun createSQLExecutor(context: Context, dbName: String, dbVersion: Int) : SQLExecutor {
            return SQLExecutor(context, dbName, dbVersion)
        }

    }
    fun closeDb(){
        nonRoomDb.close()
    }
    fun resetPointer(tableName: String) {
//        val nonRoomDb = DBHelper(context, dbName, dbVersion)
        nonRoomDb.writableDatabase.execSQL("DELETE FROM sqlite_sequence WHERE name='$tableName';")
//        closeDb()
    }
    fun deleteAll(tableName:String): Unit {
        nonRoomDb.writableDatabase.execSQL("DELETE FROM '$tableName';")
//        closeDb()
    }

    fun select(tableName: String): Cursor {
//        val nonRoomDb = DBHelper(context, dbName, dbVersion)
        println("SELECT * FROM $tableName;")
        //        nonRoomDb.close()
        return nonRoomDb.readableDatabase.rawQuery("SELECT * FROM $tableName;", null)
//        return nonRoomDb.readableDatabase.rawQuery("Select * from  Question where topicId IN (Select Topic.id from Topic where Topic.name = :topicName);", null)

    }

    fun selectByWhere(tableName: String,where: String): Cursor {
//        var cursor = nonRoomDb.readableDatabase.rawQuery("SELECT * FROM '$tableName' where Result.resultGroup_id =1;", null)
        //        return cursor
        return nonRoomDb.readableDatabase.rawQuery("SELECT * FROM $tableName where $where;", null)
    }


}