package com.example.noteappjetpackcompose.data

import com.example.noteappjetpackcompose.data.local.repository.LocalRepositoryImpl
import com.example.noteappjetpackcompose.domain.Repository
import com.example.noteappjetpackcompose.domain.model.Note

class RepositoryImpl(
    private val localRepository : LocalRepositoryImpl
) : Repository {
    override suspend fun addNote(note: Note) {
        return localRepository.addNote(note)
    }

    override suspend fun deletedNote(note: Note) {
        localRepository.deletedNote(note)
    }

    override suspend fun modifyNote(note: Note) {
        localRepository.modifyNote(note)
    }
}