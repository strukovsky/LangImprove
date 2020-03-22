package com.example.langimprove.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.db.TextRecord

class TextRecordAdapter: RecyclerView.Adapter<TextRecordAdapter.ViewHolder>() {


    val data = MutableLiveData<List<TextRecord>>();

    init{

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.text, parent, false).also {
            return ViewHolder(it)
        }
    }

    fun setData(t: List<TextRecord>)
    {
        data.value = t
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObject = data.value!![position]
        holder.title.text = currentObject.title
        holder.date.text = currentObject.time
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val date = itemView.findViewById<TextView>(R.id.date)
    }



}