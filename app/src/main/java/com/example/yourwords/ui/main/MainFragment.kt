package com.example.yourwords.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.yourwords.App
import com.example.yourwords.R
import com.example.yourwords.entity.Word
import com.example.yourwords.presentation.main.MainPresenter
import com.example.yourwords.presentation.main.MainView
import com.example.yourwords.ui.adapter.word.WordAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainView {
    private val presenter = MainPresenter(App.DB)
    private val adapter = WordAdapter { presenter.deleteWord(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)

        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val value = valueEditText.text.toString()
            val translate = translateEditText.text.toString()

            presenter.addWord(value, translate)
            valueEditText.text.clear()
            translateEditText.text.clear()
        }

        quizButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_quizFragment)
        }
    }

    override fun showAll(list: List<Word>) {
        adapter.list = list
    }

}
