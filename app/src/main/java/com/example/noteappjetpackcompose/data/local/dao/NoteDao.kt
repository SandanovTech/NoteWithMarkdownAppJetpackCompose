package com.example.noteappjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteappjetpackcompose.data.local.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Query("SELECT * from note where id = :noteId")
    fun getNoteId(noteId : String) : NoteEntity?

    @Query("Select * from note")
    fun loadNotes() : Flow<List<NoteEntity>>
}