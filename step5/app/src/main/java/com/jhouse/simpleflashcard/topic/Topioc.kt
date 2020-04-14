package com.jhouse.simpleflashcard.topic

import com.jhouse.simpleflashcard.question.Question


class Topic constructor(var name: String, var questions: Set<Question>, var filePath: String) {

    var allAnswers = mutableSetOf<String>()

    constructor(name: String, questions: Set<Question>) : this(name, questions, "noFile")

    init {
        questions.forEach { question ->
            question.answers.forEach {
                allAnswers.add(it)
            }
        }
    }
    fun getWrongAnswers(question: Question): Set<String> {
        return this.allAnswers.subtract(question.answers)
    }
}