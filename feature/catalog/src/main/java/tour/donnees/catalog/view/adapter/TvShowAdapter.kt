package tour.donnees.catalog.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tour.donnees.catalog.databinding.ItemShowBinding
import tour.donnees.domain.tvmaze.model.Show

class TvShowAdapter(private val navigation: (Show) -> Unit = {}): RecyclerView.Adapter<TvShowAdapter.ShowViewHolder>() {

    private val values = mutableListOf<Show>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            ItemShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = values[position]
        holder.image
            .load(show.image)
        holder.title.text = show.name

        holder.image.setOnClickListener {
            navigation(show)
        }
    }

    fun updateAdapter(list: List<Show>) {
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    inner class ShowViewHolder(
        binding: ItemShowBinding
    ): RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.itemShowImage
        val title: TextView = binding.itemShowTitle
    }


}