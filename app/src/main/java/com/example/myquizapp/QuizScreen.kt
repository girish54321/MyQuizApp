package com.example.myquizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myquizapp.adapter.OptionsAdapter
import com.example.myquizapp.const.Answers
import com.example.myquizapp.const.Constants
import com.example.myquizapp.const.QuestionList
import com.example.myquizapp.databinding.ActivityQuizScreenBinding

var TAG : String = "QuizScreen"

class QuizScreen : AppCompatActivity(), View.OnClickListener , OptionsAdapter.OnItemClickLister{
    private var binding: ActivityQuizScreenBinding? = null
    var currentIndex : Int = 0
    var questionData : List<QuestionList> = Constants.getQuestions()
    var optionsAdapter : OptionsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setView()
    }

    private fun setView (){

//        binding?.question1Text?.setOnClickListener ( this )
//        binding?.question2Text?.setOnClickListener ( this )
//        binding?.question3Text?.setOnClickListener ( this )
//        binding?.question4Text?.setOnClickListener ( this )
        binding?.completedProgress?.max = questionData.size
        setViewWithQuestions()
    }

    @SuppressLint("SetTextI18n")
    private fun setViewWithQuestions(){
        var data : QuestionList = questionData!![currentIndex]

        binding?.leveText?.text = "LEVEL: ${data.difficulty.uppercase()}"
        binding?.questionText?.text = data.question
        for (item in data.incorrect_answers){
            data.answersList.add(Answers(item))
        }
        data.answersList.add(Answers(data.correct_answer))
        data.answersList.shuffle()
        binding?.qptionList?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        optionsAdapter = data.answersList?.let { OptionsAdapter(it,this) }
        binding?.qptionList?.adapter = optionsAdapter
        optionsAdapter?.notifyDataSetChanged()
        binding?.completedText?.text = "${currentIndex + 1} / ${questionData!!.size}"
        binding?.completedProgress?.progress = currentIndex + 1
//        binding?.question1Text?.text = data.incorrect_answers[0]
//        binding?.question2Text?.text = data.incorrect_answers[1]
//        binding?.question3Text?.text = data.incorrect_answers[2]
//        binding?.question4Text?.text = data.correct_answer
//
//        binding?.completedProgress?.progress = currentIndex + 1
//        binding?.completedText?.text = "${currentIndex + 1} / ${questionData!!.size}"
//
//        val options = ArrayList<TextView>()
//        binding?.question1Text?.let {
//            options.add(0, it)
//        }
//        binding?.question2Text?.let {
//            options.add(1, it)
//        }
//        binding?.question3Text?.let {
//            options.add(2, it)
//        }
//        binding?.question4Text?.let {
//            options.add(3,it)
//        }
//
//        for (option in options) {
//            option.typeface = Typeface.DEFAULT
//            option.background = ContextCompat.getDrawable(
//                this,
//                R.drawable.border_radis
//            )
//            option.isClickable = true
//        }
    }

    private fun defaultOptionsView() {
//        val options = ArrayList<TextView>()
//        binding?.question1Text?.let {
//            options.add(0, it)
//        }
//        binding?.question2Text?.let {
//            options.add(1, it)
//        }
//        binding?.question3Text?.let {
//            options.add(2, it)
//        }
//        binding?.question4Text?.let {
//            options.add(3,it)
//        }
//        for (option in options) {
//            option.typeface = Typeface.DEFAULT
//            option.background = ContextCompat.getDrawable(
//                this,
//                R.drawable.border_radis
//            )
//            option.isClickable = false
//        }
    }

    private  fun isCompeted(): Boolean {
       var isLast: Boolean = false
        isLast = questionData?.size  == (currentIndex + 1)
        return isLast
    }


    private fun showNextQuestion(){
        if (isCompeted()){
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
        if (tv.text == questionData?.get(currentIndex)?.correct_answer){
            tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.right_answer_view
            )
        } else {
            tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.worng_answer_view
            )
        }
        showNextQuestion()
    }

    override fun onClick(view: View?) {
//        Log.d(TAG, Constants.getQuestions()[0].question)
//        when (view?.id) {
//            R.id.question1Text -> {
//                binding?.question1Text?.let {
//                   selectedOptionView(it,1)
//               }
//            }
//            R.id.question2Text -> {
//                binding?.question2Text?.let {
//                    selectedOptionView(it,1)
//                }
//            }
//            R.id.question3Text -> {
//                binding?.question3Text?.let {
//                    selectedOptionView(it,1)
//                }
//            }
//            R.id.question4Text -> {
//                binding?.question4Text?.let {
//                    selectedOptionView(it,1)
//                }
//            }
//        }
    }

    override fun onItemClick(position: Int) {
        println(position)
        currentIndex += 1
        Handler().postDelayed({
            setViewWithQuestions()
        }, 2000)
    }
}