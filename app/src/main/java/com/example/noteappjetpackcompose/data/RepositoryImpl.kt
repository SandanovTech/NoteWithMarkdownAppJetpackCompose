package com.example.noteappjetpackcompose.data

import com.example.noteappjetpackcompose.data.local.repository.LocalDataSourceImpl
import com.example.noteappjetpackcompose.data.mapper.mapToNoteDomain
import com.example.noteappjetpackcompose.data.mapper.mapToNoteDomainFlowList
import com.example.noteappjetpackcompose.data.mapper.mapToNoteEntity
import com.example.noteappjetpackcompose.domain.Repository
import com.example.noteappjetpackcompose.domain.model.Note
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localRepository : LocalDataSourceImpl
) : Repository {
    override suspend fun saveNote(note: Note) {
        return localRepository.saveNote(note.mapToNoteEntity())
    }

    override suspend fun deletedNote(note: Note) {
        localRepository.deletedNote(note.mapToNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        localRepository.updateNote(note.mapToNoteEntity())
    }

    override suspend fun getNoteId(noteId: String) : Note? {
        return localRepository.getNoteId(noteId)?.mapToNoteDomain()
    }

    override suspend fun loadNotes(): Flow<List<Note>> {
        return localRepository.loadNotes().mapToNoteDomainFlowList()
    }
}