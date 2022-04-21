object NoteService {
    private val notes: MutableMap<Int, Notes> = mutableMapOf();
    private val comments: MutableSet<Comments> = mutableSetOf();
    private var id: Int = 0
    private var commentsId: Int = 0

    // Notes fun ------------------

    fun add(note: Notes): Notes {
        val newNote = note.copy(noteId = ++id)
        notes[id] = newNote
        return newNote
    }

    fun delete(note: Notes): Notes? {
        if (note.delete) return null
        val delNote = note.copy(delete = true)
        notes.replace(note.noteId, delNote)
        return delNote
    }

    fun edit(note: Notes): Notes? {
        if (note.delete) return null

        val newNote = note.copy(
            noteId = note.noteId,
            title = "New title"
        )
        return notes.replace(newNote.noteId, newNote)
    }

    fun get(): List<Notes> {
        var list: List<Notes> = emptyList()
        for (item in notes) {
            if (!item.value.delete) {
                list += item.value
            }
        }
        return list
    }

    fun getById(id: Int): Notes? {
        return notes[id]
    }

    // Comments fun -------------
    fun createComment(note: Notes, comment: Comments): Comments {
        val value = notes[note.noteId] ?: throw ErrorCreateComment()
        if (value.delete) throw ErrorCreateCommentDeleteNotes()
        val tempComment = comment.copy(
            id = ++commentsId,
            noteId = note.noteId
        )
        comments.add(tempComment)
        return tempComment
    }

    fun deleteComment(comment: Comments): Boolean {
        for (value in comments) {
            if (comment.id == value.id) {
                if (value.delete) throw ErrorNotComment()
                val result = value.copy(delete = true)
                comments.remove(value)
                comments.add(result)
                return true
            }
        }
        return false
    }

    fun editComment(comment: Comments): Boolean {
        for (value in comments) {
            if (comment.id == value.id) {
                if (value.delete) throw ErrorNotComment()
                val result = value.copy(
                    id = comment.id,
                    noteId = comment.noteId,
                    message = "New message"
                )
                comments.remove(value)
                comments.add(result)
                return true
            }
        }
        return false
    }

    fun getComment(comment: Comments): Comments {
        for (value in comments) {
            if (comment === value) {
                if (!comment.delete) return value
            }
        }
        throw ErrorNotComment()
    }

    fun getComment(note: Notes): List<Comments> {
        if (notes[note.noteId] == null) throw ErrorNotNotes()
        var result: List<Comments> = emptyList()
        for (value in comments) {
            if (value.noteId == note.noteId) {
                if (!value.delete) result += value
            }
        }
        return result
    }

    fun restoreComment(comment: Comments): Comments {
        for (value in comments) {
            if (comment.id == value.id) {
                if (!value.delete) throw ErrorNotComment()
                val result = value.copy(delete = false)
                comments.remove(value)
                comments.add(result)
                return result
            }
        }
        throw ErrorNotComment()
    }
}

class ErrorNotNotes : Throwable(
    message = "Note not find"
)

class ErrorNotComment : Throwable(
    message = "Комментарий не существует, или он удален"
)

class ErrorCreateCommentDeleteNotes : Throwable(
    message = "Переданный Note удалён"
)

class ErrorCreateComment : Throwable(
    message = "Переданный Note не существует"
)

