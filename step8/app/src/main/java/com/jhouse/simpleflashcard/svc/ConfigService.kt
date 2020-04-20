package com.jhouse.simpleflashcard.svc

import android.content.Context
import com.jhouse.simpleflashcard.db.FlashCardDB
import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.helper.SingletonHolder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ConfigService private constructor(context: Context){

    private val logger: Logger = LoggerFactory.getLogger(ConfigService::class.java)
    private var db: FlashCardDB = FlashCardDB.getInstance(context)

    companion object : SingletonHolder<ConfigService, Context>(::ConfigService)

    fun addConfig(configEntity: ConfigEntity): Unit {
        db.configDao().insertConfig(configEntity)
    }

    fun updatConfig(configEntity: ConfigEntity) {
        db.configDao().updateConfig(configEntity)
    }

}
