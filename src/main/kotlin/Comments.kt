class Comments(
    val noteId: Int = 0,
    val ownerId: Int = 0,
    val replyTo: Int =0,
    val message: String,
    val delete: Boolean = false
)
