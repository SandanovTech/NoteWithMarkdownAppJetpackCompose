package com.example.noteappjetpackcompose.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

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
            .padding(8.dp)
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