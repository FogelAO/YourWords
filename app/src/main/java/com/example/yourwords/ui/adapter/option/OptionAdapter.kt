package com.example.yourwords.ui.adapter.option

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yourwords.R
import com.example.yourwords.entity.Quiz
import kotlinx.android.synthetic.main.item_quiz_option.view.*

class OptionAdapter(private val onClickListener: (String) -> Unit) : RecyclerView.Adapter<OptionAdapter.ViewHolder>() {
    var quiz = Quiz()
        set(value) {
            field = value
            showAnswers = false
            notifyDataSetChanged()
        }
    var showAnswers = false

    override fun getItemCount() = quiz.options.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = quiz.options[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var item: String = ""

        init {
            itemView.setOnClickListener {
                if (!showAnswers) {
                    onClickListener.invoke(item)
                    showAnswers = true
                    if (item != quiz.answer)
                        it.setBackgroundColor(Color.RED)
                    notifyDataSetChanged()
                }
            }
        }

        fun bind(item: String) {
            this.item = item

            itemView.apply {
                optionTextView.text = item
                if (!showAnswers)
                    setBackgroundColor(Color.WHITE)
                if (showAnswers && item == quiz.answer) {
                    setBackgroundColor(Color.GREEN)
                }
            }
        }
    }
}