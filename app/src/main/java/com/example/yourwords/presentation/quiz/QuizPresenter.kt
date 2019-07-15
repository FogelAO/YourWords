package com.example.yourwords.presentation.quiz

import com.arellomobile.mvp.InjectViewState
import com.example.yourwords.entity.Word
import com.example.yourwords.model.db.AppDatabase
import com.example.yourwords.model.repository.WordRepository
import com.example.yourwords.presentation.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@InjectViewState
class QuizPresenter(db: AppDatabase) : BasePresenter<QuizView>() {
    private val repository = WordRepository(db.wordDao())
    private lateinit var queue: Queue<Word>
    private lateinit var selectedWord: Word
    private var correct = 0
    private var wrong = 0

    override fun onFirstViewAttach() {
        GlobalScope.launch(Dispatchers.IO) {
            queue = ArrayDeque(repository.getRandom(10))
            selectWord()
        }
    }

    fun onClick(item: String) {
        if (item == selectedWord.translate) {
            viewState.showCorrect()
            correct++
        } else {
            viewState.showWrong()
            wrong++
        }

        selectWord()
    }

    private fun selectWord() {
        if (queue.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO) {
                selectedWord = queue.poll() ?: throw Exception("NPE")
                val options = repository.getRandom(3, selectedWord.translate).map { it.translate }.toMutableList().apply {
                    add(selectedWord.translate)
                    shuffle()
                }

                GlobalScope.launch(Dispatchers.Main) {
                    viewState.showWord(selectedWord.value, options)
                }
            }
        } else {
            viewState.showResults(correct.toString(), wrong.toString())
        }
    }
}