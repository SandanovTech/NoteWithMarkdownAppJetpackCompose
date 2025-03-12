package com.example.noteappjetpackcompose.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.navigation.AppScreens
import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun MainScreen(navController: NavController) {
    val viewModel: MainViewModel = koinViewModel()
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AppScreens.NoteScreen.route)
            }) {
                Icon(Icons.Default.Create, null)
            }
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val notes = viewModel.note.value
            items(viewModel.listNotesSize.value) {
                CardNote(notes[it]) {
                    navController.navigate("${AppScreens.NoteScreen.route}?noteId=${notes[it].id}")
                }
            }
        }
    }
}

@Composable
fun CardNote(note: Note, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = note.title, fontSize = 20.sp)
        }
    }
}