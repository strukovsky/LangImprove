package com.example.langimprove.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.langimprove.db.TestQuestion
import com.example.langimprove.db.TestQuestionRepository

class TestQuestionViewModel public constructor(application: Application): AndroidViewModel(application){
    val textId = MutableLiveData<Int>().also { it.value = 0 }
    val lang = MutableLiveData<String>().also { it.value = "ru" }
    val repository = TestQuestionRepository.getInstance(application)
    val questions = MutableLiveData<ArrayList<TestQuestion>>().also {
        it.value = repository.createTest(textId.value!!, lang.value!!)
    }

    fun changeContents(newTextId: Int, newLang: String)
    {
        questions.value = repository.createTest(newTextId, newLang)
    }
}