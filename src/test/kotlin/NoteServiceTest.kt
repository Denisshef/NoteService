import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        // arrange
        val note = Notes(title = "Notes first")
        // act
        val result = NoteService.add(note)
        // assert
        assertEquals(0, result?.noteId?.compareTo(1))
    }

    @Test
    fun delete() {
        // arrange
        val note = NoteService.add(Notes(title = "Notes second"))
        // act
        val result = NoteService.delete(note)
        // assert
        assertEquals(result, result)
    }

    @Test
    fun edit() {
        // arrange
        val note = NoteService.add(Notes(title = "Notes third"))
        // act
        val result = NoteService.edit(note)
        // assert
        assertEquals(note?.noteId, result?.noteId)
    }
}