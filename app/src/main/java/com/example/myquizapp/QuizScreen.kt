package com.example.myquizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myquizapp.adapter.OptionsAdapter
import com.example.myquizapp.apiClinet.RetrofitInstance
import com.example.myquizapp.const.Answers
import com.example.myquizapp.const.Constants
import com.example.myquizapp.const.QuestionList
import com.example.myquizapp.databinding.ActivityQuizScreenBinding
import com.example.myquizapp.modal.QuestionsBase
import com.example.myquizapp.modal.Results
import retrofit2.HttpException
import java.io.IOException

class QuizScreen : AppCompatActivity(), View.OnClickListener , OptionsAdapter.OnItemClickLister{
    private var TAG = "QuizScreen"
    private var binding: ActivityQuizScreenBinding? = null
    var currentIndex : Int = 0
    private var questionData : ArrayList<Results?>? = null
    var optionsAdapter : OptionsAdapter? = null
    var userScore: Int = 0
    var isLoading : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getUpComeData()
    }

    private fun getUpComeData(){
        val questionsBase: QuestionsBase? = intent.getSerializableExtra("questionsBase") as QuestionsBase
        if(questionsBase != null) {
            questionData = questionsBase!!.results as ArrayList<Results?>?
            binding?.userScore?.text = "$userScore / ${questionData?.size}"
            setView()
        }
    }

    private fun setView () {
        binding?.completedProgress?.max = questionData!!.size
        setViewWithQuestions()
    }

    private fun setViewWithQuestions() {
        var data : Results = questionData!![currentIndex]!!

        binding?.leveText?.text = "LEVEL: ${data.difficulty!!.uppercase()}"
        binding?.questionText?.text = Html.fromHtml(data.question)
        for (item in data.incorrectAnswers){
            data.answersList.add(Answers(item))
        }
        data.answersList.add(Answers(data.correctAnswer!!))
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
        questionData!![currentIndex]!!.answersList[position].isSelected = true
        var rightAnswer = questionData!![currentIndex]!!.correctAnswer
        var selectedAnswer = questionData!![currentIndex]!!.answersList[position]
        questionData!![currentIndex]!!.answersList[position].isSelected = true
        if (rightAnswer == selectedAnswer.title){
            questionData!![currentIndex]!!.answersList[position].isCorrectAnswers = true
            userScore += 1
            binding?.userScore?.text = "$userScore / ${questionData?.size}"
        }else {
            questionData!![currentIndex]!!.answersList[position].isCorrectAnswers = false
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