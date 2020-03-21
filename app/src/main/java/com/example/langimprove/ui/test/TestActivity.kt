package com.example.langimprove.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.adapter.TestQuestionAdapter
import com.example.langimprove.viewmodel.TestViewModel

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val test = findViewById<RecyclerView>(R.id.test)
        val testViewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        val testAdapter = TestQuestionAdapter(this)
        test.layoutManager = LinearLayoutManager(this)
        test.adapter = testAdapter
        testViewModel.questions.observe(this, Observer { t -> testAdapter.setData(t) })
    }
}
