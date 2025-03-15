package com.example.noteappjetpackcompose.data.local.repository

import com.example.noteappjetpackcompose.data.local.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveNote(note: NoteEntity)
    suspend fun deletedNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun getNoteId(noteId: String): NoteEntity?
    fun loadNotes() : Flow<List<NoteEntity>>
}