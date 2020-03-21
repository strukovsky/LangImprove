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
                        super.onCreate(db)
                            //  db.execSQL("INSERT INTO")
                    }
                }).build()
        }

    }
    
}