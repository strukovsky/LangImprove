package com.example.langimprove.db

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [
    Text::class, Paragraph::class, Title::class, Question::class, Answer::class
],
    version = 1,
    exportSchema = false
    )
abstract class AppDatabase: RoomDatabase() {
    abstract fun textDao(): TextDao
    abstract fun paragraphDao(): ParagraphDao
    abstract fun titleDao(): TitleDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao


    
    companion object{
        private val DATABASE_NAME: String = "APP_DATABASE"
        @Volatile
        private var instance: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase
        {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also {instance = it}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object: RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        db.execSQL("DELETE FROM texts")
                        db.execSQL("DELETE FROM paragraphs")
                        db.execSQL("INSERT INTO texts VALUES(0, 1584623328)")
                        db.execSQL("INSERT INTO paragraphs VALUES(0, 0, 0, 'ru', 'Привет, я Миша')")
                        db.execSQL("INSERT INTO paragraphs VALUES(1, 0, 1, 'fr', 'Je suis leleve ')")
                        db.execSQL("INSERT INTO questions VALUES(0, 0, 'ru',  'Как зовут главного героя?', 'c')")
                        db.execSQL("INSERT INTO questions VALUES(1, 0, 'ru',  'Кто он?', 'b')")
                        db.execSQL("INSERT INTO answers VALUES(0, 0, 'a', 'ru', 'Виктор')")
                        db.execSQL("INSERT INTO answers VALUES(1, 0, 'b', 'ru', 'Боря')")
                        db.execSQL("INSERT INTO answers VALUES(2, 0, 'c', 'ru', 'Миша')")
                        db.execSQL("INSERT INTO answers VALUES(3, 1, 'a', 'ru', 'Повар')")
                        db.execSQL("INSERT INTO answers VALUES(4, 1, 'b', 'ru', 'Ученик')")
                        db.execSQL("INSERT INTO answers VALUES(5, 1, 'c', 'ru', 'Программист')")
                        db.execSQL("INSERT INTO titles VALUES(0, 0, 'ru', 'О Мише')")

                        db.execSQL("INSERT INTO texts VALUES(1, 1584123328)")
                        db.execSQL("INSERT INTO paragraphs VALUES(2, 1, 0, 'ru', 'Привет, меня зовут Боб.')")
                        db.execSQL("INSERT INTO paragraphs VALUES(3, 1, 1, 'fr', 'J habite pres de l usine')")
                        db.execSQL("INSERT INTO questions VALUES(2, 1, 'ru',  'Как зовут главного героя?', 'b')")
                        db.execSQL("INSERT INTO questions VALUES(3, 1, 'ru',  'Где он живет', 'c')")
                        db.execSQL("INSERT INTO answers VALUES(6, 2, 'a', 'ru', 'Майкл')")
                        db.execSQL("INSERT INTO answers VALUES(7, 2, 'b', 'ru', 'Боб')")
                        db.execSQL("INSERT INTO answers VALUES(8, 2, 'c', 'ru', 'Вася')")

                        db.execSQL("INSERT INTO answers VALUES(9, 3, 'a', 'ru', 'В Москве')")
                        db.execSQL("INSERT INTO answers VALUES(10, 3, 'b', 'ru', 'В Праге')")
                        db.execSQL("INSERT INTO answers VALUES(11, 3, 'c', 'ru', 'Возле завода')")

                        db.execSQL("INSERT INTO titles VALUES(1, 1, 'ru', 'О Бобе')")

                    }}).build()

        }

    }
    
}