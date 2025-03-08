package com.example.noteappjetpackcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteappjetpackcompose.data.local.dao.NoteDao
import com.example.noteappjetpackcompose.data.local.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(NoteConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}