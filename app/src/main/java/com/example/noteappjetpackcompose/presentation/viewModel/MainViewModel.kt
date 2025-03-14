package com.example.noteappjetpackcompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappjetpackcompose.domain.Repository
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val _state = MutableStateFlow<NoteState>(NoteState.Initial)
    val state = _state.asStateFlow()

    private var _note = MutableStateFlow<List<Note>>(emptyList())
    val note = _note.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadNotes().collect {notes ->
                _note.value = notes
            }
        }
    }

    companion object {
        private const val LOG_TAG = "MainViewModel"
    }
}