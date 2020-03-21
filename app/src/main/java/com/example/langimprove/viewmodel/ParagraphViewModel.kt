package com.example.langimprove.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.langimprove.db.ParagraphRepository
import com.example.langimprove.db.TitleRepository


class ParagraphViewModel public constructor(
        application: Application
      /*  var textId: Int,
        var langs: Array<String>*/): AndroidViewModel(application) {

        var textId: Int
        var langs: Array<String>

        init {

            textId = 0
            langs = arrayOf("ru", "fr")
        }

        private val paragraphRepository = ParagraphRepository.getInstance(application)
        private val titleRepository = TitleRepository.getInstance(application)
        val paragraphs = paragraphRepository.buildText(textId, langs)
        //val title = titleRepository.getTitle(textId, langs[0])

    }

