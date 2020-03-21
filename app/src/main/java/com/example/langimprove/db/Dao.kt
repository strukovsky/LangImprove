package com.example.langimprove.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TextDao{
    @Query("SELECT * FROM texts")
    fun getText(): LiveData<List<Paragraph>>


}


@Dao
interface ParagraphDao{
    @Query("SELECT * FROM paragraphs WHERE text_id=:text_id and lang=:lang and position=:position")
    fun getParagraphs(text_id: Int, lang: String, position: Int) : Paragraph

}

@Dao
interface TitleDao{
    @Query("SELECT * FROM titles WHERE text_id=:text_id and lang=:lang")
    fun getTitle(text_id: Int, lang: String): Title
}

@Dao
interface QuestionDao{
    @Query("SELECT * FROM questions WHERE text_id=:text_id and lang=:lang")
    fun getQuestions(text_id: Int, lang: String): List<Question>
}

@Dao
interface AnswerDao{
    @Query("SELECT * FROM answers WHERE question_id=:question_id and lang=:lang ORDER BY label")
    fun getAnswers(question_id: Int, lang: String): List<Answer>

    @Query("SELECT contents FROM answers WHERE question_id=:question_id and lang=:lang ORDER BY label")
    fun getAnswersText(question_id: Int, lang: String): List<String>
}