package tour.donnees.catalog.extansion

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tour.donnees.catalog.view.CatalogActivity
import tour.donnees.catalog.viewmodel.CatalogViewModel


fun <T> Activity.toObserve(life: () -> Lifecycle, live: LiveData<T>, block: (T) -> Unit) {
    live.observe(life, block)
}


fun <T> Fragment.toObserve(live: LiveData<T>, block: (T) -> Unit) {
    live.observe(this.viewLifecycleOwner, block)
}


fun View.showIf(should: Boolean) {
    this.visibility = if (should) View.VISIBLE else View.GONE
}


fun RecyclerView.LayoutManager?.isLastItemVisible(lastIndex: Int): Boolean {
    this as LinearLayoutManager
    return (this.findLastCompletelyVisibleItemPosition() == lastIndex)
}
