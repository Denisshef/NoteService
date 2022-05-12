package data

data class Comment(
    val id: Int = 0,
    val noteId: Int,
    val ownerId: Int = 0,
    val replyTo: Int =0,
    val message: String = "Message",
    val delete: Boolean = false
)