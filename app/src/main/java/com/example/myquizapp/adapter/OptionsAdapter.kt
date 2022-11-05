package com.example.myquizapp.adapter

import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myquizapp.R
import com.example.myquizapp.const.Answers
import com.example.myquizapp.databinding.QuizItemsBinding

class OptionsAdapter(
    val items: MutableList<Answers>,
    private val listener: OnItemClickLister
    ) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: QuizItemsBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        var text = binding.questionOptionItem
        init {
            binding.root.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            var i = adapterPosition
            if(i != RecyclerView.NO_POSITION) {
                listener.onItemClick(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            QuizItemsBinding.inflate(
//                LayoutInflater.from(parent.context),
//            ),
//            parent,
//            false
//        )
        return ViewHolder(
            QuizItemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var modalItem: Answers =  items[position]
        holder.text.root.text = Html.fromHtml(modalItem.title)
        if (modalItem.isCorrectAnswers){
            holder.text.root.setBackgroundResource(R.drawable.right_answer_view)
        } else if (!modalItem.isCorrectAnswers && modalItem.isSelected){
            holder.text.root.setBackgroundResource(R.drawable.worng_answer_view)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickLister {
        fun onItemClick(position: Int)
    }

}