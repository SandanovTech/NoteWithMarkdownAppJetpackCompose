package com.example.noteappjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.noteappjetpackcompose.domain.model.Note

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)
    @Delete
    fun deleteNote(note: Note)
}