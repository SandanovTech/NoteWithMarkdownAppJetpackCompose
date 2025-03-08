package com.example.noteappjetpackcompose.domain

import com.example.noteappjetpackcompose.domain.model.Note

interface Repository {
    suspend fun addNote(note : Note)
    suspend fun deletedNote(note : Note)
    suspend fun modifyNote(note : Note)
}