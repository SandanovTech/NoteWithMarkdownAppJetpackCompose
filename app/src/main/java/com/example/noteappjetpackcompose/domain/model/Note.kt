package com.example.noteappjetpackcompose.domain.model

import java.time.LocalDateTime

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val created: LocalDateTime,
)
