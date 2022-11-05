package com.example.myquizapp

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
import retrofit2.HttpException
import java.io.IOException


class CreateQuiz : AppCompatActivity() {
    private val TAG = "CreateQuiz"
    private var binding: ActivityCreateQuizBinding? = null
    private  var data: trivia_categories? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getQuizCategory()
        setUpTypeDropDown()
    }

    private fun getQuizCategory(){
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                data = response.body()!!
                setCategoryDropDown()
                Log.e(TAG, response.body()!!.triviaCategories?.get(0)?.name ?: "Error ")
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }

    private fun setCategoryDropDown(){
        this.let { ctx ->
            val cityAdapter =
                CategoryDropDownAdapter(ctx, R.layout.dropp, data?.triviaCategories!!)
            binding?.categoryDropDown?.setAdapter(cityAdapter)
            binding?.categoryDropDown?.setOnItemClickListener { parent, _, position, _ ->
                val city = cityAdapter.getItem(position) as TriviaCategories?
                binding?.categoryDropDown?.setText(city?.name)
            }
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
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, questionsTypeList)
        binding?.typeDropDown?.setAdapter(typeAdapter)
    }

    private fun setUpTypeDropDown(){
        binding?.typeDropDown?.setOnItemClickListener { adapterView, view, i, l ->
            Log.e(TAG,i.toString())
        }
    }

}

