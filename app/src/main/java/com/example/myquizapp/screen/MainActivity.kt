package com.example.myquizapp.screen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.myquizapp.R
import com.example.myquizapp.apiClinet.RetrofitInstance
import com.example.myquizapp.databinding.ActivityMainBinding
import com.example.myquizapp.helper.BasicAlertDialog
import com.example.myquizapp.helper.LoadingScreen
import com.example.myquizapp.modal.QuestionsBase
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyQuizApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.createQuizBtn?.setOnClickListener {
            val intent = Intent(this, CreateQuiz::class.java)
            startActivity(intent)
        }
        binding?.anyTopicQuizBtn?.setOnClickListener {
            getRandomQuestion()
        }
    }

    private fun getRandomQuestion(){
        val context: Context = this
        LoadingScreen.displayLoadingWithText(this,"Please wait...",false)
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getRandomQuestion(10)
            } catch(e: IOException) {
                LoadingScreen.hideLoading()
                BasicAlertDialog.displayBasicAlertDialog(context,"Error","IOException, you might not have internet connection",false)
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                LoadingScreen.hideLoading()
                BasicAlertDialog.displayBasicAlertDialog(context,"Error","HttpException, unexpected response",false)
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
               val data: QuestionsBase  = response.body()!!
                LoadingScreen.hideLoading()
                Log.e(TAG, data.results[0].category!!)
                goToNext(data)
            } else {
                LoadingScreen.hideLoading()
                BasicAlertDialog.displayBasicAlertDialog(context,"Error","Response not successful",false)
                Log.e(TAG, "Response not successful")
            }
        }
    }

    private fun goToNext(questionsBase: QuestionsBase?) {
        Intent(this, QuizScreen::class.java).also {
            it.putExtra("questionsBase",questionsBase)
            startActivity(it)
        }
    }

}