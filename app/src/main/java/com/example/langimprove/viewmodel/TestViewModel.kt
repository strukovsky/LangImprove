package com.example.langimprove.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.langimprove.db.TestRepository

class TestViewModel public constructor(application: Application): AndroidViewModel(application){
    val questions = TestRepository.getInstance(application).createTest(0, "ru")
}