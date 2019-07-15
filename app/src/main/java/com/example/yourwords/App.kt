package com.example.yourwords

import android.app.Application
import androidx.room.Room
import com.example.yourwords.model.db.AppDatabase

class App : Application() {
    companion object {
        lateinit var DB: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        DB = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "DB-name"
        ).build()
    }
}