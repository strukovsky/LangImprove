package com.example.langimprove.db

import android.app.Application

class TitleRepository private constructor(
   application: Application
) {

    val titleDao: TitleDao = AppDatabase.getInstance(application.applicationContext).titleDao()

    fun getTitle(text_id: Int, lang: String) = titleDao.getTitle(text_id, lang)

    fun getTitles(lang: String) = titleDao.getTitles(lang)

    companion object {
        @Volatile
        var instance: TitleRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this)
            {
                return instance ?: TitleRepository(application).also { instance = it }
            }

    }

}