package tour.donnees.catalog.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tour.donnees.catalog.R
import tour.donnees.catalog.databinding.ItemEpisodeBinding
import tour.donnees.catalog.extansion.showIf
import tour.donnees.domain.tvmaze.model.Episode

class TvShowEpisodeAdapter: RecyclerView.Adapter<TvShowEpisodeAdapter.EpisodeViewHolder>() {
    private val values = mutableListOf<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = values[position]
        holder.apply {
            seasonNumber.text = String.format(
               seasonNumber.context.getString(R.string.item_season_label),
               episode.season)

            episodeNumber.text = String.format(
                seasonNumber.context.getString(R.string.item_episode_label),
                episode.number)

            episodeName.text = episode.name

            seasonNumber.showIf(episode.number == 1)
        }
    }

    fun updateAdapter(list: List<Episode>) {
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    inner class EpisodeViewHolder(binding: ItemEpisodeBinding): RecyclerView.ViewHolder(binding.root) {
        val seasonNumber: TextView = binding.itemSeasonNumber
        val episodeNumber: TextView = binding.itemEpisodeNumber
        val episodeName: TextView = binding.itemEpisodeName

    }
}