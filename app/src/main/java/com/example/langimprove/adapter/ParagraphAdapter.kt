package com.example.langimprove.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.db.Paragraph

class ParagraphAdapter internal constructor(context: Context): RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paragraph, parent, false)
        return ParagraphViewHolder(view)
    }

    var data = ArrayList<Paragraph>()



    override fun onBindViewHolder(holderParagraph: ParagraphAdapter.ParagraphViewHolder, position: Int) {
        holderParagraph.paragraph.text = data[position].contents ?: ""
    }

    override fun getItemCount() = data.size

    public fun setData(other: List<Paragraph>)
    {
        data = other as ArrayList<Paragraph>
        notifyDataSetChanged()
    }

    inner class ParagraphViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
         val paragraph = view.findViewById<TextView>(R.id.contents)
    }


}

