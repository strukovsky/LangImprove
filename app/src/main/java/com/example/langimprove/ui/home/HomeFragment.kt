package com.example.langimprove.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.langimprove.R
import com.example.langimprove.adapter.ParagraphAdapter
import com.example.langimprove.adapter.TestQuestionAdapter
import com.example.langimprove.db.Paragraph

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val paragraphViewModel = ViewModelProviders.of(this)
            .get(ParagraphViewModel::class.java)


        val paragraphAdapter = ParagraphAdapter(this.context!!)
        val root = inflater.inflate(R.layout.fragment_home, container, false) as LinearLayout
        val text = root.findViewById<RecyclerView>(R.id.text)
        text.layoutManager = LinearLayoutManager(this.context!!)
        text.adapter = paragraphAdapter

        paragraphViewModel.paragraphs.observe(this,
            Observer<List<Paragraph>> { t -> paragraphAdapter.setData(t!!) })
        paragraphViewModel.langs.observe(this,
            Observer { t ->
                run {
                    val langs = t ?: arrayOf("ru", "fr")//TODO: HERE CHANGE
                    val textId = paragraphViewModel.textId.value ?: 0
                    paragraphViewModel.changeContents(textId, langs)
                }
            }
        )
        paragraphViewModel.textId.observe(this,
            Observer { t ->
                run {
                    val textId = t ?: 0
                    val langs = paragraphViewModel.langs.value ?: arrayOf("ru", "fr")//TODO: HERE CHANGE
                    paragraphViewModel.changeContents(textId, langs)
                }
            })

        val currentTextTitle = root.findViewById<TextView>(R.id.title)
        val currentTextTime = root.findViewById<TextView>(R.id.date)
        paragraphViewModel.textRecord.observe(
            this,
            Observer { t ->
                run {
                    currentTextTitle?.text = t.title
                    currentTextTime?.text = t.time
                }
            }
        )


        val test = root.findViewById<RecyclerView>(R.id.test)
        val testViewModel = ViewModelProviders.of(this).get(TestQuestionViewModel::class.java)
        val testAdapter = TestQuestionAdapter(root.context!!)
        test.layoutManager = LinearLayoutManager(root.context!!)
        test.adapter = testAdapter

        testViewModel.textId.observe(this, Observer { t ->
            testViewModel.changeContents(t, testViewModel.lang.value!!)
        })
        testViewModel.lang.observe(this, Observer { t ->
            testViewModel.changeContents(testViewModel.textId.value!!, t)
        })
        testViewModel.questions.observe(this, Observer { t -> testAdapter.setData(t) })


        val test_start = Button(this.context)
        test_start.setOnClickListener {
            paragraphViewModel.textId.value = 1
            testViewModel.textId.value = 1

        }
        root.addView(test_start)

        val controller = activity?.findNavController(R.id.nav_host_fragment)
        controller?.addOnDestinationChangedListener { controller, destination, arguments ->
            run {
                paragraphViewModel.textId.value = arguments?.getInt("CHOICE")
            }
        }

        return root
    }


}