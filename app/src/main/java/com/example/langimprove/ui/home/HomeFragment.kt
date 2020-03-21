package com.example.langimprove.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.adapter.ParagraphAdapter
import com.example.langimprove.adapter.TestQuestionAdapter
import com.example.langimprove.db.Paragraph
import com.example.langimprove.ui.test.TestActivity
import com.example.langimprove.viewmodel.ParagraphViewModel
import com.example.langimprove.viewmodel.TestViewModel

class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val langs = arrayOf("ru", "fr")
        val textId = 0

        val paragraphViewModel = ViewModelProviders.of(this)
            .get(ParagraphViewModel::class.java)

        val paragraphAdapter = ParagraphAdapter(this.context!!)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
       // root.isNestedScrollingEnabled = false
        val text = root.findViewById<RecyclerView>(R.id.text)
        text.layoutManager = LinearLayoutManager(this.context!!)
        text.adapter = paragraphAdapter

        paragraphViewModel.paragraphs.observe(this,
            Observer<List<Paragraph>> { t -> paragraphAdapter.setData(t!!) })

        val test_start = root.findViewById<Button>(R.id.test_start)
        test_start.setOnClickListener {
            val intent = Intent(activity?.baseContext, TestActivity::class.java)
            activity?.startActivity(intent)
        }

        return root
    }
}