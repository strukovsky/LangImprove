package com.example.langimprove.viewmodel

import androidx.lifecycle.ViewModel
import com.example.langimprove.db.TitleRepository

class TitleViewModel internal constructor(titleRepository: TitleRepository, lang: String): ViewModel()
{
    val titles = titleRepository.getTitles(lang)
}