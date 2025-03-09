package com.example.noteappjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.noteappjetpackcompose.data.local.model.NoteEntity

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)
}