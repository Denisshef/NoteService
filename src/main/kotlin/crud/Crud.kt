package crud

interface Crud<E> {

    val entities: Map<Int, E>
    var id: Int

    fun add(entity: E): E
    fun delete(entity: E): Boolean
    fun edit(entity: E): Boolean
    fun getAll(): List<E>
    fun getById(id: Int): E
    fun restore(id: Int): E
}