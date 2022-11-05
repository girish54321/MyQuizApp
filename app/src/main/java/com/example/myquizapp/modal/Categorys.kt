package com.example.myquizapp.apiClinet

import com.google.gson.annotations.SerializedName


data class trivia_categories (
//    val trivia_categories: List<TriviaCategory>
    @SerializedName("trivia_categories" ) var triviaCategories : ArrayList<TriviaCategories> = arrayListOf()
)

data class TriviaCategories (
    val id: Long,
    val name: String
)
