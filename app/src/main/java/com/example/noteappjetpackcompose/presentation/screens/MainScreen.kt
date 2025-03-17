package com.example.noteappjetpackcompose.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteappjetpackcompose.R
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteState
import com.example.noteappjetpackcompose.presentation.navigation.AppScreens
import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

private const val LOG_TAG = "MainScreen"

@Composable
internal fun MainScreen(navController: NavController) {
    val viewModel: MainViewModel = koinViewModel()
    val notes by viewModel.note.collectAsState()
    val state by viewModel.state.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AppScreens.NoteScreen.route)
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

            is NoteState.GetNote -> {

            }

            NoteState.Initial -> {

            }

            NoteState.Loading -> {

            }

            is NoteState.GetNotes -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(notes.size) {
                        CardNote(note = notes[it]) {
                            navController.navigate("${AppScreens.NoteScreen.route}?noteId=${notes[it].id}")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardNote(modifier: Modifier = Modifier, note: Note, onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val viewModel: MainViewModel = koinViewModel()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .combinedClickable(onClick = {
                onClick()
            },
                onLongClick = {
                    expanded = true
                })
            .shadow(1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = note.title, fontSize = 20.sp)
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                Text("Опция 1", modifier = Modifier.padding(8.dp).clickable { viewModel.deleteNote(note) })
            }
        }
    }
}