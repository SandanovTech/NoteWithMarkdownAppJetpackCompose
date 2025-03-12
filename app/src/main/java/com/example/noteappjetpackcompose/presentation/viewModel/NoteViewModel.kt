package com.example.noteappjetpackcompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappjetpackcompose.domain.Repository
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _state: MutableStateFlow<NoteState> = MutableStateFlow(NoteState.Initial)
    val state: StateFlow<NoteState> = _state.asStateFlow()

    fun saveOrUpdateNote(note: Note) {
        _state.value = NoteState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getNoteId(note.id) == null) {
                repository.saveNote(note)
            }
            repository.updateNote(note)
            _state.value = NoteState.UpdateNote(note)
        }
    }

    fun loadNoteById(noteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = repository.getNoteId(noteId)
            if (note != null) {
                _state.value = NoteState.GetNote(note)
            } else {
                _state.value = NoteState.Error
            }
        }
    }
}