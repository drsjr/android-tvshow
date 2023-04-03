package tour.donnees.catalog.util

import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO
import java.math.BigInteger

abstract class Pagination<T>(
    var items: Int = 0,
    private var page: Int = 0,
    private val collection: MutableList<T> = mutableListOf()
) {
    fun nextPage(): Int = ++page
    fun hasMore(): Boolean = items < collection.size
    fun addAll(newItems: Collection<T>) = collection.addAll(newItems)
    fun getCollection(): Collection<T> = collection
    abstract fun nextItems(): Collection<T>
    abstract fun currentItems(): Collection<T>
}

class PaginationImpl(
    page: Int = 0,
    items: Int = 0
): Pagination<ShowDTO>(page, items) {
    override fun nextItems(): Collection<ShowDTO> {
        if ((items + 10) < getCollection().size) {
            items += 10
        } else {
            items = getCollection().size
        }
        return currentItems()
    }

    override fun currentItems(): Collection<ShowDTO> {
        return getCollection().toList().subList(BigInteger.ZERO.toInt(), items)
    }
}