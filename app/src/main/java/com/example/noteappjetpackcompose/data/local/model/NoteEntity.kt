package com.example.noteappjetpackcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val mainDescription: String,
    val created: LocalDateTime,
)
