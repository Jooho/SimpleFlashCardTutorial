package com.jhouse.simpleflashcard.db.question

import androidx.room.*

@Dao
interface QuestionDao {

    @Insert
    fun insertQuestions(questions: Set<QuestionEntity>)

    @Query("Select * from Question")
    fun getQuestions(): Array<QuestionEntity>

    @Delete
    fun deleteQuestions(questions: Set<QuestionEntity>)

}