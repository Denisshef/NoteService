fun main() {
    val noteFirst = Notes(title = "NOTE 1", text = "Text note first")
    val noteSecond = Notes(title = "NOTE 2", text = "Text note second")

    NoteService.add(noteFirst)
    NoteService.add(noteSecond)
    NoteService.delete(noteFirst)

    println(NoteService.get())

}