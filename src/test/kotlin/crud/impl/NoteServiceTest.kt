package crud.impl

import data.Note
import exception.ErrorRestoreNote
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        // arrange
        val note = Note(title = "Note first")
        // act
        val result = NoteService.add(note)
        // assert
        assertNotEquals(result, null)
    }

    @Test
    fun delete() {
        // arrange
        val note = NoteService.add(Note(title = "Notes second"))
        // act
        val result = NoteService.delete(note)
        // assert
        assertTrue(result)
    }

    @Test
    fun edit() {
        // arrange
        val note = NoteService.add(Note(title = "Notes third"))
        // act
        val result = NoteService.edit(note)
        // assert
        assertTrue(result)
    }

    @Test
    fun getAll() {
        // arrange
        NoteService.add(Note(title = "New notes"))
        // act
        val result = NoteService.getAll()
        // assert
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun getById() {
        // arrange
        val note = NoteService.add(Note(title = "New notes"))
        val id = note.noteId
        // act
        val result = NoteService.getById(id)
        // assert
        assertTrue(result.noteId == id)
    }

    @Test(expected = ErrorRestoreNote::class)
    fun restore() {
        // arrange
        val id = 1
        // act
        NoteService.restore(id)
        // assert
    }
}