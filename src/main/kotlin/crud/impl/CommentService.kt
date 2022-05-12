package crud.impl

import crud.Crud
import data.Comment
import data.Note
import exception.ErrorCreateComment
import exception.ErrorNotComment
import exception.ErrorNotNote

object CommentService : Crud<Comment> {

    private val notes: Map<Int, Note> = NoteService.entities

    override val entities: MutableMap<Int, Comment> = mutableMapOf()
    override var id: Int = 0

    override fun add(entity: Comment): Comment {
        notes[entity.noteId] ?: throw ErrorCreateComment()
        val newComment = entity.copy(
            id = ++id
        )
        entities[newComment.id] = newComment
        return newComment
    }

    override fun delete(entity: Comment): Boolean {
        if (entity.delete) throw ErrorNotComment()

        val delComment = entity.copy(
            delete = true
        )
        entities.replace(entity.id, delComment)
        return true
    }

    override fun edit(entity: Comment): Boolean {
        if (entity.delete) throw ErrorNotComment()

        val newComment = entity.copy(
            id = entity.id,
            message = "New message"
        )
        entities.replace(entity.id, newComment)
        return true
    }

    override fun getAll(): List<Comment> {
        val list: MutableList<Comment> = mutableListOf()
        for (item in entities) {
            if (!item.value.delete) {
                list += item.value
            }
        }
        return list
    }

    override fun getById(id: Int): Comment {
        val comment = entities[id] ?: throw ErrorNotComment()
        if (comment.delete) throw ErrorNotComment()

        return comment
    }

    override fun restore(id: Int): Comment {
        val restoreComment = entities[id] ?: throw ErrorNotComment()
        if (!restoreComment.delete) throw ErrorNotComment()

        val result = restoreComment.copy(
            delete = false
        )
        entities.replace(id, result)
        return result
    }
}