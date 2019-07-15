package com.example.yourwords.ui.adapter.option

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yourwords.R
import kotlinx.android.synthetic.main.item_quiz_option.view.*

class OptionAdapter(private val onClickListener: (String) -> Unit) : RecyclerView.Adapter<OptionAdapter.ViewHolder>() {
    var list: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var item: String

        init {
            itemView.setOnClickListener { onClickListener.invoke(item) }
        }

        fun bind(item: String) {
            this.item = item

            itemView.apply {
                optionTextView.text = item
            }
        }
    }
}