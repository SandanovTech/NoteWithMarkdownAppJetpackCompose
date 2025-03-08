package com.example.noteappjetpackcompose.data.local.model

import androidx.room.Entity
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "note")
data class NoteEntity(
    val id: String,
    val title: String,
    val description: String,
    val created: LocalDateTime,
)
