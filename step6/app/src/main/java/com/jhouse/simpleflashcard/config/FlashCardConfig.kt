package com.jhouse.simpleflashcard.config

import com.jhouse.simpleflashcard.question.QuestionType

data class FlashCardConfig(var questionType: QuestionType = QuestionType.CHOICES_4, var timeLimitSecs: Int = 10, var questionCount: Int = 10, var saveResult: Boolean = true
)
