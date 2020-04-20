package com.jhouse.simpleflashcard.player

import com.jhouse.simpleflashcard.question.QuestionType

data class Config(var questionType: QuestionType = QuestionType.CHOICES_4, var timeLimitSecs: Int = 10, var questionCount: Int = 10, var saveResult: Boolean = true
)
