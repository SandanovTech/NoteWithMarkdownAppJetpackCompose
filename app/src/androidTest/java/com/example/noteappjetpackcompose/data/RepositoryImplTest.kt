package com.example.noteappjetpackcompose.data

import com.example.noteappjetpackcompose.domain.Repository
import com.example.noteappjetpackcompose.domain.model.Note
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDateTime

class RepositoryImplTest {

    companion object {
        val repository = mockk<Repository>()
        val mocNote = Note(
            id = "1",
            title = "MocTitle",
            description = "MocDescription",
            mainDescription = "MocMainDescription",
            created = LocalDateTime.now()
        )
    }

    @Test
    fun saveNote() = runTest {
        coEvery { repository.saveNote(any()) } returns Unit
        repository.saveNote(mocNote)
        coVerify { repository.saveNote(mocNote) }
    }

    @Test
    fun deleteNote() = runTest {
        coEvery { repository.deleteNote(any()) } returns Unit
        repository.deleteNote(mocNote)
        coVerify { repository.deleteNote(mocNote) }
    }

    @Test
    fun updateNote() = runTest {
        val updatedNote = mocNote.copy(
            id = "2",
            title = "UpdatedTitle",
            description = "UpdatedDescription",
            mainDescription = "UpdatedMainDescription"
        )
        coEvery { repository.updateNote(any()) } returns Unit
        repository.updateNote(updatedNote)
        coVerify { repository.updateNote(updatedNote) }
    }
}