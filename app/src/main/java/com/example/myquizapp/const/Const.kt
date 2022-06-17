package com.example.myquizapp.const

object Constants {
    // TODO  Create a constant variables which we required in the result screen
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestionType(): ArrayList<QuestionType> {
        val questionsList = ArrayList<QuestionType>()
        val que1 = QuestionType(
            "Easy"
        )
        questionsList.add(que1)
        return questionsList
    }

    fun getCategory(): List<Category> {
        var categoryList = listOf(Category (
            "General Knowledge",
            9
        ),
            Category (
                "Entertainment: Books",
                10
            ),
            Category (
                "Entertainment: Film",
                11
            ),
            Category (
                "Entertainment: Music",
                12
            ),
            Category (
                "Entertainment: Musicals &amp; Theatres",
                13
            ),
            Category (
                "Entertainment: Television",
                14
            ),
            Category (
                "Entertainment: Video Games",
                15
            ),
            Category (
                "Entertainment: Board Games",
                16
            ),
            Category (
                "Science &amp; Nature",
                17
            ),
            Category (
                "Science: Computers",
                18
            ),
            Category (
                "Science: Mathematics",
                19
            ),
            Category (
                "Mythology",
                20
            ),
            Category (
                "Sports",
                21
            ),
            Category (
                "Geography",
                22
            ),
            Category (
                "History",
                23
            ),
            Category (
                "Politics",
                24
            ),
            Category (
                "Art",
                25
            ),
            Category (
                "Celebrities",
                26
            ),
            Category (
                "Animals",
                27
            ),
            Category (
                "Vehicles",
                28
            ),
            Category (
                "Entertainment: Comics",
                29
            ),
            Category (
                "Science: Gadgets",
                30
            ),
            Category (
                "Entertainment: Japanese Anime & Manga",
                31
            ),
            Category (
                "Entertainment: Cartoon & Animations",
                32
            ))

        return categoryList.toList()
    }

}