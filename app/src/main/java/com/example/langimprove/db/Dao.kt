package com.example.langimprove.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextDao{
    @Insert
    fun insert(t: Text)

    @Query("SELECT * FROM texts WHERE id=:text_id")
    fun getText(text_id: Int): Text

    @Query("SELECT * FROM texts")
    fun getAll(): List<Text>


}


@Dao
interface ParagraphDao{
    @Query("SELECT * FROM paragraphs WHERE text_id=:text_id and lang=:lang and position=:position")
    fun getParagraphs(text_id: Int, lang: String, position: Int) : Paragraph

    @Query("SELECT * FROM paragraphs")
    fun getAll(): List<Paragraph>

    @Insert
    fun insert(p: Paragraph)
}

@Dao
interface TitleDao{
    @Query("SELECT * FROM titles WHERE text_id=:text_id and lang=:lang")
    fun getTitle(text_id: Int, lang: String): Title

    @Query("SELECT * FROM titles WHERE lang=:lang")
    fun getTitles(lang: String): List<Title>
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