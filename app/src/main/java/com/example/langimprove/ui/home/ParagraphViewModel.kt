package com.example.langimprove.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.langimprove.db.Paragraph
import com.example.langimprove.db.ParagraphRepository
import com.example.langimprove.db.TextRecord
import com.example.langimprove.db.TextRecordRepository


class ParagraphViewModel public constructor(
    application: Application
    /*  var textId: Int,
      var langs: Array<String>*/
) : AndroidViewModel(application) {

    val textId = MutableLiveData<Int>().also { it.value = 0 }
    val langs = MutableLiveData<Array<String>>().also { it.value = arrayOf("ru", "fr") }

    private val paragraphRepository = ParagraphRepository.getInstance(application)
    private val titleRepository = TextRecordRepository.getInstance(application)
    var paragraphs = MutableLiveData<List<Paragraph>>()
        .also { it.value = paragraphRepository.buildText(textId.value!!, langs.value!!) }


    var textRecord = MutableLiveData<TextRecord>().also {
        it.value = titleRepository.getTextRecord(textId.value!!, langs.value!![0])
    }

    fun changeContents(newTextId: Int, newLangs: Array<String>) {
        paragraphs.value = paragraphRepository.buildText(newTextId, newLangs)
        textRecord.value = titleRepository.getTextRecord(newTextId, newLangs[0])
    }

}

