package com.example.noteappjetpackcompose.data.mapper

import com.example.noteappjetpackcompose.data.local.model.NoteEntity
import com.example.noteappjetpackcompose.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun NoteEntity.mapToNoteDomain(): Note {
    return Note(this.id, this.title, this.description, this.mainDescription, this.created)
}

fun Note.mapToNoteEntity(): NoteEntity {
    return NoteEntity(this.id, this.title, this.description, this.mainDescription, this.created)
}

fun List<NoteEntity>.mapToNoteDomainList() : List<Note> {
    return this.map { it.mapToNoteDomain() }
}

fun MutableStateFlow<NoteEntity>.mapToNoteDomainList() : Flow<Note> {
    return this.map { it.mapToNoteDomain() }
}

fun List<Note>.mapToNoteEntityList() : List<NoteEntity> {
    return this.map { it.mapToNoteEntity() }
}