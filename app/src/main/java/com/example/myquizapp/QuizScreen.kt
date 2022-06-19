package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myquizapp.const.Constants
import com.example.myquizapp.const.QuestionList

var TAG : String = "QuizScreen"

class QuizScreen : AppCompatActivity(), View.OnClickListener {

    var leve : TextView? = null
    var questionText : TextView? = null
    var question2Text : TextView? = null
    var question3Text : TextView? = null
    var question4Text : TextView? = null
    var question1Text : TextView? = null

    var completedProgress : ProgressBar? = null
    var completedText : TextView? = null

    var currentIndex : Int = 0

    var questionData : List<QuestionList>? = Constants.getQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_screen)
        setView()
    }

    fun setView (){
        leve = findViewById<TextView>(R.id.leveText)
        questionText = findViewById<TextView>(R.id.questionText)

        question1Text = findViewById<TextView>(R.id.question1Text)
        question2Text = findViewById<TextView>(R.id.question2Text)
        question3Text = findViewById<TextView>(R.id.question3Text)
        question4Text = findViewById<TextView>(R.id.question4Text)

        completedProgress = findViewById<ProgressBar>(R.id.completedProgress)
        completedProgress?.max = questionData!!.size
        completedText = findViewById<TextView>(R.id.completedText)

        question1Text?.setOnClickListener ( this )
        question2Text?.setOnClickListener ( this )
        question3Text?.setOnClickListener ( this )
        question4Text?.setOnClickListener ( this )

        setViewWithQuestions()
    }

    private fun setViewWithQuestions(){
        var data : QuestionList = questionData!![currentIndex]

        leve?.text = data.difficulty
        questionText?.text = data.question

        question1Text?.text = data.incorrect_answers[0]
        question2Text?.text = data.incorrect_answers[1]
        question3Text?.text = data.incorrect_answers[2]
        question4Text?.text = data.correct_answer

        completedProgress?.progress = currentIndex + 1
        completedText?.text = "${currentIndex + 1} / ${questionData!!.size}"

        val options = ArrayList<TextView>()
        question1Text?.let {
            options.add(0, it)
        }
        question2Text?.let {
            options.add(1, it)
        }
        question3Text?.let {
            options.add(2, it)
        }
        question4Text?.let {
            options.add(3,it)
        }

        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.border_radis
            )
            option.isClickable = true
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        question1Text?.let {
            options.add(0, it)
        }
        question2Text?.let {
            options.add(1, it)
        }
        question3Text?.let {
            options.add(2, it)
        }
        question4Text?.let {
            options.add(3,it)
        }
        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.border_radis
            )
            option.isClickable = false
        }
    }


    private fun showNextQuestion(){
        if (questionData?.size  == (currentIndex + 1)){
            Toast.makeText(this,"All Done Here!",Toast.LENGTH_LONG).show()
            return
        }
        currentIndex += 1
        Handler().postDelayed({
            setViewWithQuestions()
        }, 2000)
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        Log.d(TAG, tv.text as String)
//        tv.setTextColor(
//            Color.parseColor("#363A43")
//        )
//        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.color.black
        )
        showNextQuestion()
    }

    override fun onClick(view: View?) {
        Log.d(TAG, Constants.getQuestions()[0].question)
        when (view?.id) {
            R.id.question1Text -> {
                question1Text?.let {
                   selectedOptionView(it,1)
               }
            }
            R.id.question2Text -> {
                question2Text?.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.question3Text -> {
                question3Text?.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.question4Text -> {
                question4Text?.let {
                    selectedOptionView(it,1)
                }
            }
        }
    }
}