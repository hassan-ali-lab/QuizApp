package com.example.quizapp

data class Question(
    var id: Int,
    var question: String,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var right_option: String
)
