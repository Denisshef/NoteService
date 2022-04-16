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
        assertNotEquals(result, null)
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

    @Test
    fun get() {
        // arrange
        NoteService.add(Notes(title = "New notes"))
        // act
        val result = NoteService.get()
        // assert
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun getById() {
        // arrange
        val note = NoteService.add(Notes(title = "New notes"))
        val id = note?.noteId ?: 0
        // act
        val result = NoteService.getById(id)
        // assert
        assertTrue(result?.noteId == id)
    }
}