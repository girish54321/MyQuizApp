package com.example.myquizapp.apiClinet

import com.example.myquizapp.modal.QuestionsBase
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("api_category.php")
    suspend fun getTodos(): Response<trivia_categories>

    @GET("api.php")
    suspend fun getRandomQuestion(
        @Query("amount") latitude: Int
    ): Response<QuestionsBase>
}
