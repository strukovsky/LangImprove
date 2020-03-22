package com.example.langimprove.ui.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.langimprove.db.TextRecord
import com.example.langimprove.db.TextRecordRepository

class TextRecordViewModel public constructor(application: Application): AndroidViewModel(application)
{
    val lang =  MutableLiveData<String>().also{ it.value = "ru"}
    private val repository = TextRecordRepository.getInstance(application)
    val allTextRecords = MutableLiveData<List<TextRecord>>().also { it.value = repository.getTextRecords(lang.value!!)}

}