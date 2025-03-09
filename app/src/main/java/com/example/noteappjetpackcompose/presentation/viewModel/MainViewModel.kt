package com.example.noteappjetpackcompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappjetpackcompose.domain.Repository
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<NoteState>(NoteState.Initial)
    val state = _state.asStateFlow()

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
            _state.value = NoteState.GetNote(note)
        }
    }
}