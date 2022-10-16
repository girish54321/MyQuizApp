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

class QuizScreen : AppCompatActivity(), View.OnClickListener , OptionsAdapter.OnItemClickLister{
    private var binding: ActivityQuizScreenBinding? = null
    var currentIndex : Int = 0
    var questionData : List<QuestionList> = Constants.getQuestions()
    var optionsAdapter : OptionsAdapter? = null
    var isLoading : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setView()
    }

    private fun setView (){
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
        isLoading = false
        binding?.qptionList?.adapter = optionsAdapter
        optionsAdapter?.notifyDataSetChanged()
        binding?.completedText?.text = "${currentIndex + 1} / ${questionData!!.size}"
        binding?.completedProgress?.progress = currentIndex + 1
    }

    private  fun isCompeted(): Boolean {
       var isLast: Boolean = false
        isLast = questionData?.size  == (currentIndex + 1)
        return isLast
    }


    private fun showNextQuestion(){
        isLoading = true
        if (isCompeted()){
            Toast.makeText(this,"All Done Here!",Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                finish()
            }, 2000)
            return
        }
        currentIndex += 1
        Handler().postDelayed({
            setViewWithQuestions()
        }, 2000)
    }

    private fun onSelectItem(position: Int){
        if (isLoading){
            return
        }
        questionData[currentIndex].answersList[position].isSelected = true
        var rightAnswer = questionData[currentIndex].correct_answer
        var selectedAnswer = questionData[currentIndex].answersList[position]
        questionData[currentIndex].answersList[position].isSelected = true
        if (rightAnswer == selectedAnswer.title){
            questionData[currentIndex].answersList[position].isCorrectAnswers = true
            Toast.makeText(this, "Right Answer Bro / Sis", Toast.LENGTH_SHORT).show()
        }else {
            questionData[currentIndex].answersList[position].isCorrectAnswers = false
            Toast.makeText(this, "Dam! you missed", Toast.LENGTH_SHORT).show()
        }
        optionsAdapter?.notifyDataSetChanged()
        showNextQuestion()
    }

    override fun onItemClick(position: Int) {
        onSelectItem(position)
    }

    override fun onClick(p0: View?) {

    }
}