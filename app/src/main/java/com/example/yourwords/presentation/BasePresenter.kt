package com.example.yourwords.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {
}