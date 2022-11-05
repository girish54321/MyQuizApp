package com.example.myquizapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myquizapp.apiClinet.trivia_categories
import com.example.myquizapp.apiClinet.RetrofitInstance
import com.example.myquizapp.apiClinet.TriviaCategories
import com.example.myquizapp.adapter.CategoryDropDownAdapter
import com.example.myquizapp.const.Constants
import com.example.myquizapp.databinding.ActivityCreateQuizBinding
import com.example.myquizapp.helper.BasicAlertDialog
import com.example.myquizapp.helper.LoadingScreen
import com.example.myquizapp.modal.QuestionsBase
import retrofit2.HttpException
import java.io.IOException


class CreateQuiz : AppCompatActivity() {
    private val TAG = "CreateQuiz"
    private var binding: ActivityCreateQuizBinding? = null
    private var data: trivia_categories? = null

    private var categoryIndex: Int? = null
    private var amountIndex: Int? = null
    private var difficultyIndex: Int? = null
    private var typeIndex: Int? = null
    var typeAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getQuizCategory()
        setUpTypeDropDown()
        setUpDiffcultyDropDown()

        binding?.startBtn?.setOnClickListener {
            getQuizQuestion()
        }
    }

    private fun getQuizCategory(){
        LoadingScreen.displayLoadingWithText(this,"Please wait...",false)
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch(e: IOException) {
                LoadingScreen.hideLoading()
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                LoadingScreen.hideLoading()
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                data = response.body()!!
                setCategoryDropDown()
                LoadingScreen.hideLoading()
                Log.e(TAG, response.body()!!.triviaCategories?.get(0)?.name ?: "Error ")
            } else {
                Log.e(TAG, "Response not successful")
                LoadingScreen.hideLoading()
            }
        }
    }

    private fun setCategoryDropDown(){
        val cityAdapter =
            CategoryDropDownAdapter(this, R.layout.dropp, data?.triviaCategories!!)
        binding?.categoryDropDown?.setAdapter(cityAdapter)
        binding?.categoryDropDown?.setOnItemClickListener { parent, _, position, _ ->
            val data = cityAdapter.getItem(position) as TriviaCategories?
            binding?.categoryDropDown?.setText(data?.name)
            categoryIndex = position
        }
        val difficultyList = ArrayList<String>()
        for (item in Constants.getDifficulty()) {
            difficultyList.add(item.title)
        }
        val questionsTypeList = ArrayList<String>()
        for (item in Constants.getQuestionsTypeList()) {
            questionsTypeList.add(item.title)
        }
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, difficultyList)
        binding?.diffcultyDropDown?.setAdapter(adapter)
        typeAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, questionsTypeList)
        binding?.typeDropDown?.setAdapter(typeAdapter)
    }

    private fun setUpDiffcultyDropDown(){
        binding?.diffcultyDropDown?.setOnItemClickListener { adapterView, view, i, l ->
        difficultyIndex = i
        }
    }

    private fun setUpTypeDropDown(){
        binding?.typeDropDown?.setOnItemClickListener { adapterView, view, i, l ->
            typeIndex = i
        }
    }

    private fun checkFormData(): Boolean {
        if(binding?.numberOfQution?.text.toString() == ""){
            binding?.numberOfQution?.error = "Enter Number of Question"
            BasicAlertDialog.displayBasicAlertDialog(this,"Error","Enter Number of Question.",false)
            return false
        }
        if(categoryIndex == null){
            BasicAlertDialog.displayBasicAlertDialog(this,"Error","Please select Category.",false)
            return false
        }
        if(difficultyIndex == null){
            BasicAlertDialog.displayBasicAlertDialog(this,"Error","Please select Difficulty.",false)
            return false
        }
        if(typeIndex == null){
            BasicAlertDialog.displayBasicAlertDialog(this,"Error","Please select Type.",false)
            return false
        }
        return true
    }

    private fun getQuizQuestion(){
        var context: Context = this
        if(!checkFormData()){
            return
        }
        LoadingScreen.displayLoadingWithText(this,"Please wait...",false)
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getCutomQuestion(binding?.numberOfQution?.text.toString(),
                    data!!.triviaCategories[categoryIndex!!].id,
                    Constants.getDifficulty()[difficultyIndex!!].value,
                    Constants.getQuestionsTypeList()[typeIndex!!].value,
                )
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
                val data: QuestionsBase = response.body()!!
                LoadingScreen.hideLoading()
                Log.e(TAG, data.results.toString())
                Log.e(TAG, data.results[0].question!!)
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

