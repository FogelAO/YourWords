package com.example.yourwords.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yourwords.entity.Word
import com.example.yourwords.model.dao.WordDao

@Database(entities = [Word::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
}