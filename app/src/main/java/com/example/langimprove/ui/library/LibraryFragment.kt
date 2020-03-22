package com.example.langimprove.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.adapter.TextRecordAdapter
import kotlinx.android.synthetic.main.test_question.*

class LibraryFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val choiceInLibrary = MutableLiveData<Int>()
        val textRecordViewModel =
            ViewModelProviders.of(this).get(TextRecordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_library, container, false)
        val records: RecyclerView = root.findViewById(R.id.records)
        records.layoutManager = LinearLayoutManager(this.context)
        val adapter = TextRecordAdapter(choiceInLibrary)
        records.adapter = adapter


        val controller = activity?.findNavController(R.id.nav_host_fragment)

        choiceInLibrary.value = -1
        choiceInLibrary.observe(this, Observer {
            if(it != -1)
            {
                val args = Bundle()
                args.putInt("CHOICE", it)
                controller?.navigate(R.id.navigation_home, args)
            }
        })

        textRecordViewModel.allTextRecords.observe(this, Observer {
            t -> run {
                adapter.setData(t!!)
        }
        })

        return root
    }
}