package com.example.kuralify.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.LocalDateTime


@Entity("kural_table")
@TypeConverters(com.example.kuralify.db.Converters::class)
data class KuralEntity @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate =  true)
    val id : Int =0 ,
    val prompt : String?,
    val ids : List<Int>?,
)