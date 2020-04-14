package com.jhouse.simpleflashcard

import android.graphics.Color
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.jhouse.simpleflashcard.config.FlashCardConfig
import com.jhouse.simpleflashcard.question.Question
import com.jhouse.simpleflashcard.question.QuestionFile
import com.jhouse.simpleflashcard.topic.Topic
import com.jhouse.simpleflashcard.topic.TopicManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
