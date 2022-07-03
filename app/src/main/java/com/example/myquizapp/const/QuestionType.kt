package com.example.myquizapp.const

data class QuestionType(
    val typeTitle:String
)
data class Cities(
    val definition: String,
    val id: String
)
data class Category(
    val title: String,
    val value: Int
)

data class QuestionList(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: MutableList<String>,
    var answersList: MutableList<Answers> = arrayListOf()
)

data class Answers (
    val title: String,
    var isSelected: Boolean = false,
    var isCorrectAnswers: Boolean = false
)

