package tour.donnees.catalog.extansion

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

fun <T : RecyclerView.ViewHolder?> RecyclerView.Adapter<T>.getLastIndex(): Int {
    return this.itemCount -1
}
