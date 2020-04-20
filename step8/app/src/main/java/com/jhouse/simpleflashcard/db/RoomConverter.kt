package com.jhouse.simpleflashcard.db

import androidx.room.TypeConverter
import com.jhouse.simpleflashcard.question.QuestionType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RoomConverter {
    private val logger: Logger = LoggerFactory.getLogger(RoomConverter::class.java)

    @TypeConverter
    fun questionTypeEnumToString(value: QuestionType) = value.toString()
    @TypeConverter
    fun questionTypeStringToEnum(value: String) = QuestionType.valueOf(value)
}