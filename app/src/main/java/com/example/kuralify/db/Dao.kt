package com.example.kuralify.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kuralify.model.KuralEntity

@Dao
interface Dao {
    @Query("SELECT * FROM kural_table ORDER BY id DESC")
    fun getHistory(): LiveData<List<KuralEntity>>

    @Insert(entity = KuralEntity::class)
    suspend fun addEntity(entity: KuralEntity)

    @Delete
    suspend fun deleteEntity(entity: KuralEntity)
}