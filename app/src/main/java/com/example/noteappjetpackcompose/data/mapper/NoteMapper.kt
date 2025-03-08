package com.example.noteappjetpackcompose.data.mapper

import com.example.noteappjetpackcompose.data.local.model.NoteEntity
import com.example.noteappjetpackcompose.domain.model.Note

fun NoteEntity.mapToNoteDomain(noteEntity: NoteEntity) : Note {
    return Note(noteEntity.id, noteEntity.title, noteEntity.description, noteEntity.created)
}

fun Note.mapToNoteEntity(note: Note) : NoteEntity {
    return NoteEntity(note.id, note.title, note.description, note.created)
}