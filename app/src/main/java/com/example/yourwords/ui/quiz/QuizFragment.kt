package com.example.yourwords.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yourwords.App
import com.example.yourwords.R
import com.example.yourwords.presentation.quiz.QuizPresenter
import com.example.yourwords.presentation.quiz.QuizView
import com.example.yourwords.ui.adapter.option.OptionAdapter
import kotlinx.android.synthetic.main.fragment_quiz.*

class QuizFragment : Fragment(), QuizView {
    private val presenter = QuizPresenter(App.DB)
    private val adapter = OptionAdapter { presenter.onClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_quiz, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)

        okButton.setOnClickListener {
            findNavController().navigateUp()
        }
        recyclerView.adapter = adapter
    }

    override fun showWord(value: String, translate: List<String>) {
        valueTextView.text = value
        adapter.list = translate
    }

    override fun showCorrect() {
        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
    }

    override fun showWrong() {
        Toast.makeText(context, "Wrong!", Toast.LENGTH_SHORT).show()
    }

    override fun showResults(correct: String, wrong: String) {
        recyclerView.isVisible = false
        valueTextView.isVisible = false

        resultsLabel.isVisible = true

        correctAnswersLabel.isVisible = true
        correctAnswersCountTextView.apply {
            isVisible = true
            text = correct
        }

        wrongAnswersLabel.isVisible = true
        wrongAnswersCountTextView.apply {
            isVisible = true
            text = wrong
        }
        okButton.isVisible = true
    }

}