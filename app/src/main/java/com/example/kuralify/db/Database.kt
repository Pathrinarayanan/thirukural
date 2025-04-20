package com.example.kuralify.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kuralify.model.KuralEntity

@Database(entities = [KuralEntity::class], version =  1)
@TypeConverters(com.example.kuralify.db.Converters::class)
abstract class Database: RoomDatabase() {
    companion object{
        const val name = "kural_db"
    }
    abstract fun getDao(): Dao
}