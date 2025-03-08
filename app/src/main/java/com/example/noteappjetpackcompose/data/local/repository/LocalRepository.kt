package com.example.noteappjetpackcompose.data.local.repository

import com.example.noteappjetpackcompose.domain.model.Note

interface LocalRepository {
    suspend fun addNote(note : Note)
    suspend fun deletedNote(note : Note)
    suspend fun modifyNote(note : Note): Note
}