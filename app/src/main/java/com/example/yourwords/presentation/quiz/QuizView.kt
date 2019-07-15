package com.example.yourwords.presentation.quiz

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuizView : MvpView {

    fun showWord(value: String, translate: List<String>)

    fun showCorrect()

    fun showWrong()

    fun showResults(correct: String, wrong: String)
}