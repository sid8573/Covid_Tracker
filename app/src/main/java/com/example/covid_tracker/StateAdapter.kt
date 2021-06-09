package com.example.covid_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_layout.view.*

class StateAdapter (val list : List<StatewiseItem>) : BaseAdapter(){
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_layout,parent,false)
        val item = list[position]
        view.confirm.text= item.confirmed
        view.state.text= item.state
        view.recover.text= item.recovered
        view.death.text= item.deaths
        view.active.text= item.active
        return view

    }

}