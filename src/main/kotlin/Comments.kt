data class Comments(
    val id: Int = 0,
    val noteId: Int = 0,
    val ownerId: Int = 0,
    val replyTo: Int =0,
    val message: String = "Message",
    val delete: Boolean = false
)