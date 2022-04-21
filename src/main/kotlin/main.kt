fun main() {
    val noteFirst = NoteService.add(Notes(title = "NOTE 1", text = "Text note first"))

    val comment = NoteService.createComment(
        noteFirst,
        Comments(message = "Comment text")
    )
    val comment1 = NoteService.createComment(
        noteFirst,
        Comments(message = "Comment text1")
    )

    println(NoteService.deleteComment(comment))
    println(NoteService.restoreComment(comment))
}