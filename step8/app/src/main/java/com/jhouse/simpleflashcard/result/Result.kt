package com.jhouse.simpleflashcard.result

import com.jhouse.simpleflashcard.db.result.ResultEntity


class Result(val resultEntity: ResultEntity) {

    constructor(
        resultGroupId: Int,
        questionId: Int,
        responseAnswer: String,
        rightAnswer: String,
        correct: Boolean
    ) : this(ResultEntity(resultGroupId, questionId, responseAnswer, rightAnswer, correct))

}