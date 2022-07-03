package com.example.myquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myquizapp.adapter.CategoryDropDownAdapter
import com.example.myquizapp.const.Category
import com.example.myquizapp.const.Constants
import com.example.myquizapp.databinding.ActivityCreateQuizBinding

class CreateQuiz : AppCompatActivity() {
    private var difficultyTypeList = arrayOf("Easy","Medium","Hard")
    private var categoryList = Constants.getCategory()
    private var binding: ActivityCreateQuizBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.startBtn?.setOnClickListener {
            val intent = Intent(this, QuizScreen::class.java)
            startActivity(intent)
        }

        this.let { ctx ->
            val cityAdapter =
                CategoryDropDownAdapter(ctx, R.layout.dropp, categoryList)
            binding?.categoryDropDown?.setAdapter(cityAdapter)
            binding?.categoryDropDown?.setOnItemClickListener { parent, _, position, _ ->
                val city = cityAdapter.getItem(position) as Category?
                binding?.categoryDropDown?.setText(city?.title)
            }
        }

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, difficultyTypeList)
        binding?.diffcultyDropDown?.setAdapter(adapter)

        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, difficultyTypeList)
        binding?.typeDropDown?.setAdapter(typeAdapter)
    }

}

