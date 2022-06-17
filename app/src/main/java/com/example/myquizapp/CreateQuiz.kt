package com.example.myquizapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.myquizapp.adapter.CategoryDropDownAdapter
import com.example.myquizapp.const.Category
import com.example.myquizapp.const.Cities
import com.example.myquizapp.const.Constants

class CreateQuiz : AppCompatActivity() {

    var difficultyTypeList = arrayOf("Easy","Medium","Hard")
    var categoryList = Constants.getCategory()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_quiz)

        val diffcultyTextView= findViewById<AutoCompleteTextView>(R.id.diffcultyDropDown)
        var inputText = findViewById<AppCompatEditText>(R.id.numberOfQution)

        val categoryTextView = findViewById<AutoCompleteTextView>(R.id.categoryDropDown)
        val typeTextView = findViewById<AutoCompleteTextView>(R.id.typeDropDown)

        this?.let { ctx ->
            val cityAdapter =
                CategoryDropDownAdapter(ctx, R.layout.dropp, categoryList)
            categoryTextView.setAdapter(cityAdapter)
            categoryTextView.setOnItemClickListener { parent, _, position, _ ->
                val city = cityAdapter.getItem(position) as Category?
                categoryTextView.setText(city?.title)
            }
        }

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, difficultyTypeList)
        diffcultyTextView.setAdapter(adapter)

        var typeAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, difficultyTypeList)
        typeTextView.setAdapter(typeAdapter)
    }

}

