package com.example.langimprove.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(
    tableName = "texts"
)
data class Text(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Long
)


@Entity(
    tableName = "paragraphs",
    foreignKeys = [
        ForeignKey(entity = Text::class, parentColumns = ["id"], childColumns = ["text_id"])
    ]
)
data class Paragraph(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "text_id")
    val textId: Int,
    val position: Int,
    val lang: String,
    val contents: String
)
{
    override fun toString(): String {
        return id.toString() + " " + textId.toString() + " " +  position.toString() + " " + lang.toString() + " " + contents
    }
}


@Entity(
    tableName = "titles",
    foreignKeys = [
        ForeignKey(entity = Text::class, parentColumns = ["id"], childColumns = ["text_id"])
    ]
)
data class Title(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name="text_id")
    val textId: Int,
    val lang: String,
    val title: String
)

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(entity = Text::class, parentColumns = ["id"], childColumns = ["text_id"])
    ]
)
data class Question(
    @PrimaryKey @ColumnInfo(name = "id") val id:Int,

    @ColumnInfo(name="text_id")
    val textId: Int,
    val lang: String,
    val contents: String,
    val correct: Char
)

@Entity(
    tableName = "answers",
    foreignKeys = [
        ForeignKey(entity = Question::class, parentColumns = ["id"], childColumns = ["question_id"])
    ]
)
data class Answer(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name="question_id")
    val questionId: Int,
    val label: Char,
    val lang: String,
    val contents: String

)


// Non-database object for tests. Test is an array of [TestQuestion]
data class TestQuestion(
    val question: String,
    val correct: Char,
    val A: String,
    val B: String,
    val C: String
)


// Non-database object for Text record. It has only id, title(on certain language), identifier of language, and data stamp

data class TextRecord(
    val title: String,
    val time: String
)
