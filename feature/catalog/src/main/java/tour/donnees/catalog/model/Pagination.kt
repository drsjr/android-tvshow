package tour.donnees.catalog.model

data class Pagination(
    private var page: Int = 0,
    private var showedItem: Int = 0,
    private var total: Int = 0
) {

    fun getItemPage() = showedItem

    fun hasMore() = showedItem < total

    fun getMore(): Int {
        return if ((showedItem + 10) < total) {
            showedItem += 10
            showedItem
        } else {
            showedItem = total
            total
        }
    }

    fun addNewList(total: Int) {
        this.total = total
    }

    fun loadNewPage(): Int {
        page += 1
        return page
    }
}