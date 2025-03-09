package com.example.noteappjetpackcompose.data.local.repository

import com.example.noteappjetpackcompose.data.local.dao.NoteDao
import com.example.noteappjetpackcompose.data.mapper.mapToNoteEntity
import com.example.noteappjetpackcompose.domain.model.Note

class LocalRepositoryImpl(
    private val noteDao: NoteDao
) : LocalRepository {
    override suspend fun addNote(note: Note) {
        noteDao.insertNote(note.mapToNoteEntity())
    }

    override suspend fun deletedNote(note: Note) {
        noteDao.deleteNote(note.mapToNoteEntity())
    }

    override suspend fun modifyNote(note: Note) : Note {
        return note
    }
}