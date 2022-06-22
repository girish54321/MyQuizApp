package com.example.myquizapp.const

object Constants {
    // TODO  Create a constant variables which we required in the result screen
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): List<QuestionList> {
        var qList = mutableListOf(
            QuestionList(
                "Entertainment: Cartoon & Animations",
                "multiple",
                "medium",
                "What is the fourth book of the Old Testament?",
                "Numbers",
                mutableListOf("Genesis", "Exodus", "Leviticus")
            ),
            QuestionList(
                "General Knowledge",
                "multiple",
                "easy",
                "What alcoholic drink is made from molasses?",
                "Rum",
                mutableListOf("Gin", "Vodka", "Whisky")
            ),
            QuestionList(
                "Geography",
                "multiple",
                "medium",
                "What is the capital of Australia?",
                "Canberra",
                mutableListOf("Sydney", "Melbourne", "Brisbane")
            ),
            QuestionList(
                "History",
                "multiple",
                "medium",
                "Which of the following battles is often considered as marking the beginning of the fall of the Western Roman Empire?",
                "Battle of Adrianople",
                mutableListOf("Battle of Thessalonica", "Battle of Pollentia", "Battle of Constantinople")
            )
        )
        return qList
    }

    fun getCategory(): List<Category> {
        var categoryList = listOf(
            Category(
                "General Knowledge",
                9
            ),
            Category(
                "Entertainment: Books",
                10
            ),
            Category(
                "Entertainment: Film",
                11
            ),
            Category(
                "Entertainment: Music",
                12
            ),
            Category(
                "Entertainment: Musicals &amp; Theatres",
                13
            ),
            Category(
                "Entertainment: Television",
                14
            ),
            Category(
                "Entertainment: Video Games",
                15
            ),
            Category(
                "Entertainment: Board Games",
                16
            ),
            Category(
                "Science &amp; Nature",
                17
            ),
            Category(
                "Science: Computers",
                18
            ),
            Category(
                "Science: Mathematics",
                19
            ),
            Category(
                "Mythology",
                20
            ),
            Category(
                "Sports",
                21
            ),
            Category(
                "Geography",
                22
            ),
            Category(
                "History",
                23
            ),
            Category(
                "Politics",
                24
            ),
            Category(
                "Art",
                25
            ),
            Category(
                "Celebrities",
                26
            ),
            Category(
                "Animals",
                27
            ),
            Category(
                "Vehicles",
                28
            ),
            Category(
                "Entertainment: Comics",
                29
            ),
            Category(
                "Science: Gadgets",
                30
            ),
            Category(
                "Entertainment: Japanese Anime & Manga",
                31
            ),
            Category(
                "Entertainment: Cartoon & Animations",
                32
            )
        )

        return categoryList.toList()
    }

}