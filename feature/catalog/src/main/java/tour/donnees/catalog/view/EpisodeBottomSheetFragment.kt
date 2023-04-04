package tour.donnees.catalog.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tour.donnees.catalog.R
import tour.donnees.catalog.databinding.FragmentEpisodeBottomSheetBinding
import tour.donnees.domain.tvmaze.model.Episode

class EpisodeBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEpisodeBottomSheetBinding
    private val args by navArgs<EpisodeBottomSheetFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val episode: Episode = args.episode
        with(binding) {
            itemEpisodeName.text = episode.name
            itemEpisodeNumber.text = String.format(
                context?.getString(R.string.item_episode_label).orEmpty(),
                episode.number
            )
            itemSeasonNumber.text = episode.season.toString()
            itemEpisodeSummary.text = Html.fromHtml(episode.summary, Html.FROM_HTML_MODE_COMPACT) ?: "N/A"
            itemEpisodeImage.load(episode.image) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
        }
    }
}