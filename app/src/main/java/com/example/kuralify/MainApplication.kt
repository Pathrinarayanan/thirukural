package com.example.kuralify

import android.app.Application
import androidx.room.Room
import com.example.kuralify.db.Database

class MainApplication : Application() {
    companion object{
        public lateinit var db : Database
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
           context =  applicationContext,
            name = Database.name,
            klass = Database::class.java
        ).build()
    }
}