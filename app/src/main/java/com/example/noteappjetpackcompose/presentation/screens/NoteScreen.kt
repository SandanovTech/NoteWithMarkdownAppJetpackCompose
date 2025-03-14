package com.example.noteappjetpackcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteappjetpackcompose.R
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.viewModel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.util.UUID

private const val LOG_TAG = "NoteScreen"

@Composable
internal fun NoteScreen(navController: NavController, transferNoteId: String?) {
    val viewModel: NoteViewModel = koinViewModel()
    val note = viewModel.note.collectAsState()
    val noteValue = note.value

    var title by rememberSaveable(noteValue) {
        mutableStateOf(noteValue?.title ?: "")
    }

    var description by rememberSaveable(noteValue) {
        mutableStateOf(noteValue?.description ?: "")
    }

    var textInput by rememberSaveable(noteValue) {
        mutableStateOf(noteValue?.mainDescription ?: "")
    }

    var isItalic by rememberSaveable {
        mutableStateOf(false)
    }
    var textSize by remember { mutableFloatStateOf(16f) }


    val italicTransformation = VisualTransformation { text ->
        val styledText = AnnotatedString(
            text = text.text,
            spanStyles = listOf(
                AnnotatedString.Range(
                    item = SpanStyle(fontStyle = FontStyle.Italic),
                    start = 0,
                    end = text.length
                )
            )
        )
        TransformedText(styledText, OffsetMapping.Identity)
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
                    placeholder = { Text(stringResource(R.string.label)) },
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
                    placeholder = { Text(stringResource(R.string.description)) },
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
                    IconButton(onClick = {
                        isItalic = !isItalic

                    }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_text_format_24),
                            null,
                            modifier = Modifier.padding(5.dp)
                        )
                    }

                    IconButton(onClick = {
                        textSize++
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_text_increase_24),
                            null,
                            modifier = Modifier.padding(5.dp)
                        )
                    }

                    IconButton(onClick = {
                        textSize--
                    }) { Icon(
                        painter = painterResource(R.drawable.baseline_text_decrease_24),
                        null,
                        modifier = Modifier.padding(5.dp)
                    )}


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
                    placeholder = { Text(stringResource(R.string.input_text)) },
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
                    textStyle = TextStyle(fontSize = textSize.sp),
                    visualTransformation = if (isItalic) italicTransformation else VisualTransformation.None,
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