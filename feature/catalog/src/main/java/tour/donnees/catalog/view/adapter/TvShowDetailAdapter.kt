package tour.donnees.catalog.view.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tour.donnees.catalog.R
import tour.donnees.catalog.databinding.ItemShowDetailBinding
import tour.donnees.domain.tvmaze.model.Show

class TvShowDetailAdapter: RecyclerView.Adapter<TvShowDetailAdapter.DetailViewHolder>() {
    private val values = mutableListOf<Show>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemShowDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val show = values[position]
        holder.apply {
            nameShow.text = show.name
            genreShow.text = show.genre
            releaseDateShow.text = show.schedule
            summaryShow.text = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT) ?: "N/A"
            imageShow.load(show.image) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
        }
    }

    fun updateAdapter(list: List<Show>) {
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(binding: ItemShowDetailBinding): RecyclerView.ViewHolder(binding.root) {
        val nameShow: TextView = binding.itemShowDetailName
        val genreShow: TextView = binding.itemShowDetailGenre
        val releaseDateShow: TextView = binding.itemShowDetailReleaseDate
        val summaryShow: TextView = binding.itemShowDetailSummary
        val imageShow: ImageView = binding.itemShowImage
    }
}