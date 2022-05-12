package crud.impl

import data.Comment
import data.Note
import org.junit.Test

import org.junit.Assert.*

class CommentServiceTest {

    @Test
    fun add() {
        // arrange
        NoteService.add(Note(title = "Note"))
        val comment = Comment(noteId = 1)
        // act
        val result = CommentService.add(comment)
        // assert
        assertNotEquals(result, null)
    }

    @Test
    fun delete() {
        // arrange
        NoteService.add(Note(title = "Note"))
        val comment = Comment(noteId = 1)
        // act
        val result = CommentService.delete(comment)
        // assert
        assertTrue(result)
    }

    @Test
    fun edit() {
        // arrange
        NoteService.add(Note(title = "Note"))
        val comment = Comment(noteId = 1)
        // act
        val result = CommentService.edit(comment)
        // assert
        assertTrue(result)
    }

    @Test
    fun getAll() {
        // arrange
        NoteService.add(Note(title = "Note"))
        val comment = Comment(noteId = 1)
        CommentService.add(comment)
        // act
        val result = CommentService.getAll()
        // assert
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun getById() {
        // arrange
        NoteService.add(Note(title = "Note"))
        val comment = Comment(noteId = 1)
        CommentService.add(comment)
        val id = 1
        // act
        val result = CommentService.getById(id)
        // assert
        assertTrue(id == result.id)
    }

    @Test
    fun restore() {
        // arrange
        NoteService.add(Note(title = "Note"))
        val comment = CommentService.add(Comment(noteId = 1))
        CommentService.delete(comment)
        // act
        val result = CommentService.restore(comment.id)
        // assert
        assertFalse(result.delete)
    }
}