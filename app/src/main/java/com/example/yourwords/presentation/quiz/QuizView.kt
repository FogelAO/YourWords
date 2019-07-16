package com.example.yourwords.presentation.quiz

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.yourwords.entity.Quiz

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuizView : MvpView {

    fun showWord(value: String, translate: List<String>)

    fun showQuiz(quiz: Quiz)

    fun showResults(correct: String, wrong: String)
}