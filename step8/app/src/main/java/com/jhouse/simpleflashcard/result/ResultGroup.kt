package com.jhouse.simpleflashcard.result

data class ResultGroup(
    val id: Int,
    val playerId: Int,
    val topicId: Int,
    val date: String,
    val results: List<Result>
) {}
