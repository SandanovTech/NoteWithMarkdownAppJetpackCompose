package com.example.noteappjetpackcompose.data.local.repository

import com.example.noteappjetpackcompose.data.local.dao.NoteDao
import com.example.noteappjetpackcompose.domain.model.Note

class LocalRepositoryImpl(
    private val noteDao: NoteDao
) : LocalRepository {
    override suspend fun addNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deletedNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun modifyNote(note: Note) : Note {
        return note
    }
}