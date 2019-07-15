package com.example.yourwords.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.yourwords.entity.Word

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun showAll(list: List<Word>)
}