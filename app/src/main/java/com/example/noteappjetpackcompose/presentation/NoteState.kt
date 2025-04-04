package com.example.noteappjetpackcompose.presentation

import com.example.noteappjetpackcompose.domain.model.Note

sealed interface NoteState {
    data object Initial : NoteState
    data object Error : NoteState
    data object Loading : NoteState
    data class GetNotes(val notes: List<Note>) : NoteState
}