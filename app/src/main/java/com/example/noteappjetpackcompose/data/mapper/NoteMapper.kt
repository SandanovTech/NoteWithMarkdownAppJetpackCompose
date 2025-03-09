package com.example.noteappjetpackcompose.data.mapper

import com.example.noteappjetpackcompose.data.local.model.NoteEntity
import com.example.noteappjetpackcompose.domain.model.Note

fun NoteEntity.mapToNoteDomain() : Note {
    return Note(this.id, this.title, this.description, this.created)
}

fun Note.mapToNoteEntity() : NoteEntity {
    return NoteEntity(this.id, this.title, this.description, this.created)
}