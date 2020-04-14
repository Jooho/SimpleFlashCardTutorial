package com.jhouse.simpleflashcard

import android.graphics.Color
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.jhouse.simpleflashcard.config.FlashCardConfig
import com.jhouse.simpleflashcard.question.Question
import com.jhouse.simpleflashcard.question.QuestionFile
import com.jhouse.simpleflashcard.topic.Topic
import com.jhouse.simpleflashcard.topic.TopicManager
import kotlinx.android.synthetic.main.activity_main.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.RuntimeException
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    val logger: Logger = LoggerFactory.getLogger(MainActivity::class.java)

    private lateinit var answerBtn: List<CheckBox>


    override fun onCreate(savedInstanceState: Bundle?) {
        logger.debug("System Property Set:")
        logger.debug(
            "org.apache.poi.javax.xml.stream.XMLInputFactory {}",
            System.getProperty("org.apache.poi.javax.xml.stream.XMLInputFactory")
        )
        logger.debug(
            "org.apache.poi.javax.xml.stream.XMLOutputFactory {}",
            System.getProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory")
        )
        logger.debug(
            "org.apache.poi.javax.xml.stream.XMLEventFactory {}",
            System.getProperty("org.apache.poi.javax.xml.stream.XMLEventFactory")
        )

        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLInputFactory",
            "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLOutputFactory",
            "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
            "org.apache.poi.javax.xml.stream.XMLEventFactory",
            "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val assetManager = resources.assets

        //Test Data
        TopicManager.loadFromQuestionFile(QuestionFile(assetManager.open("quiz.xlsx"), "quiz.xlsx"))
        answerBtn = listOf<CheckBox>(answerBtn1, answerBtn2, answerBtn3, answerBtn4)

        answerBtn.forEach {
            it.setOnClickListener {
                if (it is CheckBox) {
                    val checked: Boolean = it.isChecked
                    logger.debug("Answer Button:{} is checked({})", it.id, it.isChecked)

                }
            }
        }
        flashCardStart("별자리")


    }

    private fun flashCardStart(topicName: String) {
        val flashCardConfig = FlashCardConfig()
        var topic = TopicManager.getTopicByName("별자리")
        val flashCardQuestions = mutableSetOf<Question>()
        var questionNum = 0
        for (i in 0..flashCardConfig.questionCount) {
            var tempQuestion = topic.questions.random()
            while (flashCardQuestions.contains(tempQuestion)) {
                tempQuestion = topic.questions.random()
            }
            flashCardQuestions.add(tempQuestion)
        }

        displayFlashCard(flashCardQuestions.elementAt(questionNum), topic)
        submitBtn.setOnClickListener {
            when (submitBtn.text) {
                "Next" -> {
                    questionNum += 1
                    if (questionNum < flashCardConfig.questionCount) {
                        displayFlashCard(flashCardQuestions.elementAt(questionNum), topic)
                    }
                    submitBtn.setText("제출")
                }
                "제출" -> {
                    checkAnswer(flashCardQuestions.elementAt(questionNum))
                    submitBtn.setText("Next")
                }

            }
        }

    }


    private fun displayFlashCard(question: Question, topic: Topic) {


        val rightAnswers: Set<String> = question.answers
        questionDesc.setText(question.desc)
        val possibleAnswers = mutableSetOf<String>()

        possibleAnswers.addAll(rightAnswers)
        while (possibleAnswers.size < answerBtn.size) {
            possibleAnswers.add(topic.getWrongAnswers(question).random())
            logger.debug("possibleAnswers.size: {}", possibleAnswers.size)
            logger.debug("answerBtn.size: {}", possibleAnswers.size)
        }

        for ((index, btn) in answerBtn.withIndex()) {
            btn.setText(possibleAnswers.elementAt(index))
            btn.setBackgroundResource(R.drawable.answer_button)
            if (btn.isChecked) btn.toggle()
        }


    }


    private fun checkAnswer(question: Question) {

        answerBtn.forEach {
            if (it.text in question.answers) {

                it.setBackgroundColor(Color.GREEN)
            } else if (it.text !in question.answers) {
                if (it.isChecked)
                    it.setBackgroundColor(Color.RED)
            }
        }
    }
}
