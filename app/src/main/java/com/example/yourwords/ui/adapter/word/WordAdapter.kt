package com.example.yourwords.ui.adapter.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yourwords.R
import com.example.yourwords.entity.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordAdapter(private val onDeleteClickListener: (Word) -> Unit) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    var list: List<Word> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        return holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var item: Word

        init {
            itemView.deleteImageView.setOnClickListener { onDeleteClickListener.invoke(item) }
        }

        fun bind(word: Word) {
            item = word
            with(item) {
                itemView.apply {
                    valueTextView.text = value
                    translateTextView.text = translate
                }
            }
        }
    }
}