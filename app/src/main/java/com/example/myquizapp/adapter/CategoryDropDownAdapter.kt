package com.example.myquizapp.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myquizapp.R
import com.example.myquizapp.const.Category
import com.example.myquizapp.const.Cities

class CategoryDropDownAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities: List<Category>
) :
    ArrayAdapter<Category>(mContext, mLayoutResourceId, cities) {
    private val city: MutableList<Category> = ArrayList(cities)
    private var allCities: List<Category> = ArrayList(cities)

    override fun getCount(): Int {
        return city.size
    }
    override fun getItem(position: Int): Category {
        return city[position]
    }
    override fun getItemId(position: Int): Long {
        return city[position].value.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            convertView = inflater.inflate(mLayoutResourceId, parent, false)
        }
        try {
            val city: Category = getItem(position)
            val cityAutoCompleteView = convertView!!.findViewById<View>(R.id.droptext) as TextView
            cityAutoCompleteView.text = city.title
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }
}