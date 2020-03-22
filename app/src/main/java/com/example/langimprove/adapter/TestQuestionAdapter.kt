package com.example.langimprove.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.db.TestQuestion

class TestQuestionAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<TestQuestionAdapter.ViewHolder>() {

    val data = MutableLiveData<List<TestQuestion>>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.test_question, parent, false))
    }

    fun setData(new: List<TestQuestion>)
    {
        data.value = new
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.value!![position]
        holder.question.text =
            item.question
        holder.A.text = item.A
        holder.B.text = item.B
        holder.C.text = item.C
        val correct = item.correct
        holder.choice.setOnCheckedChangeListener { group, checkedId -> run{
            val choice = if(checkedId == R.id.A) "a" else if(checkedId == R.id.B) "b" else "c"
            Log.d("AZAZAZ", item.correct)
            val message = if(choice == correct) "Yes!" else "No("
            Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
        } }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val A = itemView.findViewById<RadioButton>(R.id.A)
        val B = itemView.findViewById<RadioButton>(R.id.B)
        val C = itemView.findViewById<RadioButton>(R.id.C)
        val question = itemView.findViewById<TextView>(R.id.question)
        val choice = itemView.findViewById<RadioGroup>(R.id.choice)
    }


}