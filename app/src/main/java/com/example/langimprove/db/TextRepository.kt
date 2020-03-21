package com.example.langimprove.db

import androidx.lifecycle.MutableLiveData

class TextRepository private constructor(
    private val paragraphDao: ParagraphDao
){
    suspend  fun buildText(text_id: Int, langs: Array<String>): MutableLiveData<List<Paragraph>>
    {
        val list = ArrayList<Paragraph>()
        langs.withIndex().forEach { item ->

            list.add(paragraphDao.getParagraphs(text_id, item.value, item.index))
        }
        return MutableLiveData<List<Paragraph>>().also { it.value = list  }

    }


    companion object{
        @Volatile private var instance: TextRepository? = null

        fun getInstance(paragraphDao: ParagraphDao)
        {
            instance?: synchronized(this)
            {
                instance ?: TextRepository(paragraphDao).also { instance = it }
            }
        }
    }
}