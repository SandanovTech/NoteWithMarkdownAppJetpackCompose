package com.example.noteappjetpackcompose.data.local

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteConverter {
    private val isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalTime(value: String): LocalDateTime {
        return value.let {
            LocalDateTime.parse(it, isoFormatter)
        }
    }

    @TypeConverter
    fun fromString(value : LocalDateTime) : String {
        return value.format(isoFormatter)
    }
}