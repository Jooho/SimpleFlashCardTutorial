package com.jhouse.simpleflashcard.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class SingletonHolder<out T, in A>(private val constructor: (A) -> T) {
    private val logger: Logger = LoggerFactory.getLogger(SingletonHolder::class.java)

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        return when {
            instance != null -> instance!!
            else -> synchronized(this) {
                if (instance == null) instance = constructor(arg)
                instance!!
            }
        }
    }
}
