data class Notes(
    val noteId: Int = 0,
    val title: String,
    val text: String? = null,
    val privacy: Int = 0,
    val commentsPrivacy: Int = 0,
    val privacyView: String = "no",
    val privacyComment: String = "no",
    val comments: ArrayList<Comments>? = null,
    val delete: Boolean = false
) {
    override fun toString(): String {
        return "noteId=$noteId"
    }
}