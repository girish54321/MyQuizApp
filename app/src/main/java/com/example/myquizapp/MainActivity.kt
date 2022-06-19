package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var createQuizBtn = findViewById<LinearLayoutCompat>(R.id.createQuizBtn)
        var anyTopicQuizBtn = findViewById<LinearLayoutCompat>(R.id.anyTopicQuizBtn)

        createQuizBtn.setOnClickListener {
            val intent = Intent(this, CreateQuiz::class.java)
            startActivity(intent)
        }

        anyTopicQuizBtn.setOnClickListener {
            val intent = Intent(this, QuizScreen::class.java)
            startActivity(intent)
        }
    }
}