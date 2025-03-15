package com.example.noteappjetpackcompose.domain

import com.example.noteappjetpackcompose.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveNote(note: Note)
    suspend fun deletedNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun getNoteId(noteId: String): Note?
    fun loadNotes() : Flow<List<Note>>
}