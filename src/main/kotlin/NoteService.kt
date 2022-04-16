object NoteService {
    private val notes: MutableMap<Int, Notes> = mutableMapOf();
    private val comments: MutableSet<Comments> = mutableSetOf();
    private var id: Int = 0
    private var commentsId: Int = 0

    // Notes fun ------------------

    fun add(note: Notes): Notes? {
        val newNote = note.copy(noteId = ++id)
        notes[id] = newNote
        return notes[id]
    }

    fun delete(note: Notes?): Notes? {
        if (note == null || note.delete) return null
        val delNote = note.copy(delete = true)
        return notes.replace(note.noteId, delNote)
    }

    fun edit(note: Notes?): Notes? {
        if (note == null || note.delete) return null

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

//    fun createComment() {
//
//    }
}