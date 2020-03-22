package com.example.langimprove.db

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData

class ParagraphRepository private constructor(
    application: Application
) {
    private val paragraphDao: ParagraphDao
    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        paragraphDao = database.paragraphDao()
    }

     fun buildText(text_id: Int, langs: Array<String>) =
            AsyncBuildText(paragraphDao, text_id, langs).execute().get()





    companion object {
        @Volatile
        private var instance: ParagraphRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this)
            {
                instance ?: ParagraphRepository(application).also { instance = it }
            }
    }


}

class AsyncBuildText(val paragraphDao: ParagraphDao, val text_id: Int, val langs: Array<String>): AsyncTask<Void, Void, List<Paragraph>>()
{
    override fun doInBackground(vararg params: Void?): List<Paragraph> {
        val list = ArrayList<Paragraph>()

        val all = paragraphDao.getAll()


        langs.withIndex().forEach { item ->
            run {

                list.add(paragraphDao.getParagraphs(text_id, item.value, item.index))

            }
        }
        return list;
    }

}