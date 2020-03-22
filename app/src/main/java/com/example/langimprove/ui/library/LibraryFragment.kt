package com.example.langimprove.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.adapter.TextRecordAdapter

class LibraryFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val textRecordViewModel =
                ViewModelProviders.of(this).get(TextRecordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_library, container, false)
        val records: RecyclerView = root.findViewById(R.id.records)
        records.layoutManager = LinearLayoutManager(this.context)
        val adapter = TextRecordAdapter()
        records.adapter = adapter


        textRecordViewModel.allTextRecords.observe(this, Observer {
            t -> run {
                adapter.setData(t!!)
        }
        })

        return root
    }
}