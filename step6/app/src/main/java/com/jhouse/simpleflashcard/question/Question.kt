package com.jhouse.simpleflashcard.question

class Question(val id: Int, val desc: String, private val answer: String) {
    var answers = mutableSetOf<String>()

    init {
        answer.split("/").forEach { v ->
            answers.add(v)
        }
    }



}