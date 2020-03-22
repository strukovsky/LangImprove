package com.example.langimprove.db

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TextRecordRepository private constructor(
   application: Application
) {

    val titleDao: TitleDao = AppDatabase.getInstance(application.applicationContext).titleDao()
    val textDao: TextDao = AppDatabase.getInstance(application.applicationContext).textDao()

    fun getTextRecord(text_id: Int, lang: String) =
        AsyncGetTextRecord(text_id, lang).execute().get()

    fun getTextRecords(lang: String) =
        AsyncGetTextRecords(lang).execute().get()



    companion object {
        @Volatile
        var instance: TextRecordRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this)
            {
                return instance ?: TextRecordRepository(application).also { instance = it }
            }

        // this fun is used in AsyncTasks, so it is here
        private fun getFormattedDate(time: Long) =
            SimpleDateFormat("E dd MMM", Locale.getDefault()).format(Date(time))
    }



    @SuppressLint("StaticFieldLeak")
    inner class AsyncGetTextRecord(val text_id: Int, val lang: String):AsyncTask<Void, Void, TextRecord>() {




        override fun doInBackground(vararg params: Void?): TextRecord {
            val textObject = textDao.getText(text_id)
            val titleObject = titleDao.getTitle(text_id, lang)

            val time = textObject.time
            val title = titleObject.title



            return TextRecord(title, getFormattedDate(time))
        }

    }

    @SuppressLint("StaticFieldLeak")
    inner
    class AsyncGetTextRecords(val lang: String): AsyncTask<Void, Void, List<TextRecord>>() {
        override fun doInBackground(vararg params: Void?): List<TextRecord> {
            val result = ArrayList<TextRecord>()
            val allTexts = textDao.getAll()
            for(textObject in allTexts)
            {
                val titleObject = titleDao.getTitle(textObject.id, lang)
                val title = titleObject.title
                val time = textObject.time
                val timeFormatted = getFormattedDate(time)
                result.add(TextRecord(title, timeFormatted))
            }
            return result
        }

    }


}

