package com.example.noteappjetpackcompose.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.noteappjetpackcompose.R
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteState
import com.example.noteappjetpackcompose.presentation.navigation.AppScreens
import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

private const val LOG_TAG = "MainScreen"

@Composable
internal fun MainScreen(
    viewModel: MainViewModel,
    state : NoteState,
    notes : List<Note>,
    onNewNoteScreenClickListener: () -> Unit,
    onNoteScreenClickListener: (Int) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNewNoteScreenClickListener()
            }) {
                Icon(Icons.Default.Create, null)
            }
        }) { innerPadding ->
        when (state) {
            NoteState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(R.drawable.empty_note), contentDescription = null)
                }
            }
            NoteState.Initial -> {

            }

            NoteState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is NoteState.GetNotes -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(notes.size) {
                        CardNote(note = notes[it]) {
                            onNoteScreenClickListener(it)
                        }
                    }
                }
            }
        }
    }
}