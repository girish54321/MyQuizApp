package com.example.myquizapp.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myquizapp.apiClinet.TriviaCategories
import com.example.myquizapp.R

class CategoryDropDownAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities: List<TriviaCategories>
) :
    ArrayAdapter<TriviaCategories>(mContext, mLayoutResourceId, cities) {
    private val city: MutableList<TriviaCategories> = ArrayList(cities)

    override fun getCount(): Int {
        return city.size
    }
    override fun getItem(position: Int): TriviaCategories {
        return city[position]
    }
    override fun getItemId(position: Int): Long {
        return city[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            convertView = inflater.inflate(mLayoutResourceId, parent, false)
        }
        try {
            val city: TriviaCategories = getItem(position)
            val cityAutoCompleteView = convertView!!.findViewById<View>(R.id.girishText) as TextView
            cityAutoCompleteView.text = city.name
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }
}