package com.example.langimprove.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TestRepository private constructor(application: Application)
{
    private val questionDao: QuestionDao
    private val answerDao: AnswerDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        questionDao = database.questionDao()
        answerDao = database.answerDao()
    }



     fun createTest(text_id: Int, lang: String): MutableLiveData<ArrayList<TestQuestion>>
    {

        return MutableLiveData<ArrayList<TestQuestion>>().also { it.value = AsyncCreateTest(text_id, lang).execute().get()}
        
    }




    companion object
    {
        @Volatile private var instance: TestRepository? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this)
            {
                instance ?: TestRepository(application).also { instance = it }
            }

    }


    inner class AsyncCreateTest(val text_id: Int, val lang: String): AsyncTask<Void, Void, ArrayList<TestQuestion>>()
    {
        override fun doInBackground(vararg params: Void?): ArrayList<TestQuestion> {
            val questions = questionDao.getQuestions(text_id, lang)
            val test = ArrayList<TestQuestion>()
            for(question in questions)
            {
                val question_id = question.id
                val answers = answerDao.getAnswersText(question_id, lang)
                test.add(TestQuestion(question.contents, question.correct, answers[0], answers[1], answers[2]))
            }

            return test
        }

    }
}