package com.example.langimprove.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.langimprove.db.TestQuestionRepository

class TestQuestionViewModel public constructor(application: Application): AndroidViewModel(application){
    val questions = TestQuestionRepository.getInstance(application).createTest(0, "ru")
}