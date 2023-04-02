package tour.donnees.catalog.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tour.donnees.catalog.databinding.ItemShowBinding
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO

class TvShowAdapter: RecyclerView.Adapter<ShowViewHolder>() {

    private val values = mutableListOf<ShowDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            ItemShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun getItemCount(): Int = values.size

    fun getLastIndex(): Int = values.size - 1

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = values[position]
        holder.image
            .load(show.image?.medium)
        holder.title.text = show.name
    }

    fun updateAdapter(list: List<ShowDTO>) {
        values.addAll(list)
        notifyDataSetChanged()
    }


}