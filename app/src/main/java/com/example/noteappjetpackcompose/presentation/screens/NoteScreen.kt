package com.example.noteappjetpackcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteappjetpackcompose.R
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.viewModel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.util.UUID

@Composable
internal fun NoteScreen(navController: NavController, transferNoteId: String?) {
    val viewModel: NoteViewModel = koinViewModel()
    val note = viewModel.note.collectAsState()
    val noteValue = note.value

    var title by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    var textInput by rememberSaveable {
        mutableStateOf("")
    }


    val noteId by rememberSaveable(transferNoteId) {
        mutableStateOf(transferNoteId ?: UUID.randomUUID().toString())
    }
    LaunchedEffect(noteId) {
        viewModel.loadNoteById(noteId)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .background(Color.LightGray),
                verticalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    value = title,
                    placeholder = { Text(noteValue?.title ?: stringResource(R.string.label)) },
                    onValueChange = { newText ->
                        title = newText
                        viewModel.saveOrUpdateNote(
                            note = Note(
                                id = noteId,
                                title = title,
                                description = description,
                                mainDescription = textInput,
                                created = LocalDateTime.now()
                            )
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    ),
                    maxLines = 2
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    value = description,
                    placeholder = { Text(noteValue?.description ?: stringResource(R.string.description)) },
                    onValueChange = { newText ->
                        description = newText
                        viewModel.saveOrUpdateNote(
                            note = Note(
                                id = noteId,
                                title = title,
                                description = description,
                                mainDescription = textInput,
                                created = LocalDateTime.now()
                            )
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    ),
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_text_format_24),
                        null,
                        modifier = Modifier.padding(5.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_text_increase_24),
                        null,
                        modifier = Modifier.padding(5.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_text_decrease_24),
                        null,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(10.dp),
                    value = textInput,
                    placeholder = { Text(noteValue?.mainDescription ?: stringResource(R.string.input_text)) },
                    onValueChange = { newText ->
                        textInput = newText
                        viewModel.saveOrUpdateNote(
                            note = Note(
                                id = noteId,
                                title = title,
                                description = description,
                                mainDescription = textInput,
                                created = LocalDateTime.now()
                            )
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    )
                )
            }
        }
    }
}