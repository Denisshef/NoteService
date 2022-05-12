package crud.impl

import crud.Crud
import data.Note
import exception.ErrorNotNote
import exception.ErrorRestoreNote

object NoteService : Crud<Note> {

    override val entities: MutableMap<Int, Note> = mutableMapOf()

    override var id: Int = 0

    override fun add(entity: Note): Note {
        val newNote = entity.copy(noteId = ++id)
        entities[id] = newNote
        return newNote
    }

    override fun delete(entity: Note): Boolean {
        if (entity.delete) throw ErrorNotNote()

        val delNote = entity.copy(
            delete = true
        )
        entities.replace(entity.noteId, delNote)
        return true
    }

    override fun edit(entity: Note): Boolean {
        if (entity.delete) throw ErrorNotNote()

        val newNote = entity.copy(
            noteId = entity.noteId,
            title = "New title"
        )
        entities.replace(entity.noteId, newNote)
        return true
    }

    override fun getAll(): List<Note> {
        val list: MutableList<Note> = mutableListOf()
        for (item in entities) {
            if (!item.value.delete) {
                list += item.value
            }
        }
        return list
    }

    override fun getById(id: Int): Note {
        val note = entities[id] ?: throw ErrorNotNote()
        if (note.delete) throw ErrorNotNote()

        return note
    }

    override fun restore(id: Int): Note {
        throw ErrorRestoreNote()
    }
}