package com.jhouse.simpleflashcard.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jhouse.simpleflashcard.db.config.ConfigDao
import com.jhouse.simpleflashcard.db.config.ConfigEntity
import com.jhouse.simpleflashcard.db.player.PlayerDao
import com.jhouse.simpleflashcard.db.player.PlayerEntity
import com.jhouse.simpleflashcard.db.question.QuestionDao
import com.jhouse.simpleflashcard.db.question.QuestionEntity
import com.jhouse.simpleflashcard.db.result.ResultDao
import com.jhouse.simpleflashcard.db.result.ResultEntity
import com.jhouse.simpleflashcard.db.result.PlayerResultDao
import com.jhouse.simpleflashcard.db.result.PlayerResultEntity
import com.jhouse.simpleflashcard.db.topic.TopicDao
import com.jhouse.simpleflashcard.db.topic.TopicEntity

@Database(entities = [QuestionEntity::class, TopicEntity::class, PlayerResultEntity::class, ResultEntity::class, PlayerEntity::class, ConfigEntity::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class FlashCardDB : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun topicDao(): TopicDao
    abstract fun resultGroupDao(): PlayerResultDao
    abstract fun resultDao(): ResultDao
    abstract fun playerDao(): PlayerDao
    abstract fun configDao(): ConfigDao


    companion object {
        private val DB_NAME = "FlashCardDB5"
        private var instance: FlashCardDB? = null

        fun getInstance(context: Context): FlashCardDB {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
            }
        }

        private fun buildDatabase(context: Context): FlashCardDB {
            return Room.databaseBuilder(
                context, FlashCardDB::class.java,
                DB_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).fallbackToDestructiveMigration()
                .build()
        }
    }
}