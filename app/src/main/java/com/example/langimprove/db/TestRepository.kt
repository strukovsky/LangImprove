package com.example.langimprove.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TestRepository private constructor(private val questionDao: QuestionDao, private val answerDao: AnswerDao)
{



    suspend fun createTest(text_id: Int, lang: String): MutableLiveData<ArrayList<TestQuestion>>
    {
        val questions = questionDao.getQuestions(text_id, lang)
        val test = ArrayList<TestQuestion>()
        for(question in questions)
        {
            val question_id = question.id
            val answers = answerDao.getAnswersText(question_id, lang)
            test.add(TestQuestion(question.contents, question.correct, answers))
        }
        return MutableLiveData<ArrayList<TestQuestion>>().also { it.value = test}
        
    }


    companion object
    {
        @Volatile private var instance: TestRepository? = null

        fun getInstance(questionDao: QuestionDao, answerDao: AnswerDao)
        {
            instance ?: synchronized(this)
            {
                instance ?: TestRepository(questionDao, answerDao)
            }
        }
    }
}