package com.example.noteappjetpackcompose.data.local.repository

import com.example.noteappjetpackcompose.data.local.dao.NoteDao
import com.example.noteappjetpackcompose.data.local.model.NoteEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val noteDao: NoteDao,
) : LocalDataSource {

    override suspend fun saveNote(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    override suspend fun deletedNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    override suspend fun getNoteId(noteId: String): NoteEntity? {
        return noteDao.getNoteId(noteId)
    }

    override fun loadNotes(): Flow<List<NoteEntity>> {
        return noteDao.loadNotes()
    }
}