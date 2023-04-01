package tour.donnees.catalog.view

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tour.donnees.catalog.databinding.ItemShowBinding

class ShowViewHolder(
    binding: ItemShowBinding,
    onClick: () -> Unit = {}
): RecyclerView.ViewHolder(binding.root) {
    val image: ImageView = binding.itemShowImage
    val title: TextView = binding.itemShowTitle
}