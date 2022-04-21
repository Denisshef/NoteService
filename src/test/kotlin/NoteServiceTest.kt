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
        assertEquals(note.noteId, result?.noteId)
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
        val id = note.noteId
        // act
        val result = NoteService.getById(id)
        // assert
        assertTrue(result?.noteId == id)
    }

    // Comments test
    @Test
    fun createComment() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        // act
        val result = NoteService.createComment(note, comment)
        // assert
        assertTrue(note.noteId == result.noteId)
    }

    @Test(expected = ErrorCreateComment::class)
    fun createCommentErrorCreateComment() {
        // arrange
        val comment = Comments()
        val note = Notes(title = "Note with comment")
        // act
        NoteService.createComment(note, comment)
    }

    @Test(expected = ErrorCreateCommentDeleteNotes::class)
    fun createCommentErrorCreateCommentDeleteNotes() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        // act
        NoteService.delete(note)
        NoteService.createComment(note, comment)
    }

    @Test
    fun deleteCommentTrue() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        val value = NoteService.createComment(note, comment)
        // act
        val result = NoteService.deleteComment(value)
        // assert
        assertTrue(result)
    }

    @Test
    fun deleteCommentFalse() {
        // arrange
        val comment = Comments()
        // act
        val result = NoteService.deleteComment(comment)
        // assert
        assertFalse(result)
    }

    @Test
    fun editCommentTrue() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        val value = NoteService.createComment(note, comment)
        // act
        val result = NoteService.editComment(value)
        // assert
        assertTrue(result)
    }

    @Test
    fun editCommentFalse() {
        // arrange
        val comment = Comments()
        // act
        val result = NoteService.editComment(comment)
        // assert
        assertFalse(result)
    }

    @Test
    fun getCommentTrue() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        val value = NoteService.createComment(note, comment)
        // act
        val result = NoteService.getComment(value)
        // assert
        assertEquals(value, result)
    }

    @Test(expected = ErrorNotComment::class)
    fun getCommentException() {
        // arrange
        val comment = Comments()
        // act
        val result = NoteService.getComment(comment)
    }

    @Test
    fun getAllCommentNote() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        NoteService.createComment(note, comment)
        // act
        val result = NoteService.getComment(note)
        // assert
        assertFalse(result.isEmpty())
    }

    @Test(expected = ErrorNotNotes::class)
    fun getAllCommentNoteErrorNotNotes() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        val noteNotAddNotes = Notes(title = "Not add note")
        NoteService.createComment(note, comment)
        // act
        NoteService.getComment(noteNotAddNotes)
        }

    @Test
    fun restoreCommentTrue() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        val value = NoteService.createComment(note, comment)
        NoteService.deleteComment(value)
        // act
        val result = NoteService.restoreComment(value)
        // assert
        assertEquals(value.id, result.id)
    }

    @Test(expected = ErrorNotComment::class)
    fun restoreCommentException() {
        // arrange
        val comment = Comments()
        val note = NoteService.add(Notes(title = "Note with comment"))
        //val value = NoteService.createComment(note, comment)
        // act
        NoteService.restoreComment(comment)
    }
}