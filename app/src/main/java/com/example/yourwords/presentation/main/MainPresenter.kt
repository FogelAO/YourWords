package com.example.yourwords.presentation.main

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.yourwords.entity.Word
import com.example.yourwords.model.db.AppDatabase
import com.example.yourwords.model.repository.WordRepository
import com.example.yourwords.presentation.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@InjectViewState
class MainPresenter(db: AppDatabase) : BasePresenter<MainView>() {
    private val repository = WordRepository(db.wordDao())

    override fun onFirstViewAttach() {
        GlobalScope.launch(Dispatchers.IO) {
            val list = repository.getAll()
            val random = repository.getRandom(3)

            random.forEach {
                Log.e("TAG", "id=${it.id}, value = ${it.value}")
            }

            GlobalScope.launch(Dispatchers.Main) {
                viewState.showAll(list)
            }
        }
    }

    fun addWord(value: String, translate: String) {
        if (value.trim().isNotEmpty() && translate.trim().isNotEmpty()) {
            GlobalScope.launch {
                val word = Word(
                    value = value,
                    translate = translate
                )
                repository.addWord(word)

                val list = repository.getAll()
                showList(list)
            }
        }
    }

    fun deleteWord(word: Word) {
        GlobalScope.launch {
            repository.deleteWord(word)
            val list = repository.getAll()
            showList(list)
        }
    }

    private fun showList(list: List<Word>) {
        GlobalScope.launch(Dispatchers.Main) {
            viewState.showAll(list)
        }
    }
}