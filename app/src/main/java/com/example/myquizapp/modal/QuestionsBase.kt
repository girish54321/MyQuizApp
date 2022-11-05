package com.example.myquizapp.modal

import com.example.myquizapp.const.Answers
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuestionsBase (
    @SerializedName("response_code" ) var responseCode : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf()
): Serializable

data class Results (
    @SerializedName("category"          ) var category         : String?           = null,
    @SerializedName("type"              ) var type             : String?           = null,
    @SerializedName("difficulty"        ) var difficulty       : String?           = null,
    @SerializedName("question"          ) var question         : String?           = null,
    @SerializedName("correct_answer"    ) var correctAnswer    : String?           = null,
    @SerializedName("incorrect_answers" ) var incorrectAnswers : ArrayList<String> = arrayListOf(),
    var answersList: MutableList<Answers> = arrayListOf()
) : Serializable