package com.example.yourwords.entity

data class Quiz(
    val value: String = "",
    val answer: String = "",
    val answerIndex: Int = 0,
    val options: List<String> = emptyList()
)