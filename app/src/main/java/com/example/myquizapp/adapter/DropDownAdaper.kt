package com.example.myquizapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myquizapp.R
import com.example.myquizapp.const.QuestionType

class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<QuestionType>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.dropp, parent, false)

        val recipe = getItem(position) as QuestionType
        val text = rowView.findViewById(R.id.droptext) as TextView
        text.text = "what"

        return rowView
    }

}
