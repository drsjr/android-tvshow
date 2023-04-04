package tour.donnees.catalog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import tour.donnees.catalog.databinding.FragmentTvShowDetailBinding
import tour.donnees.catalog.extansion.getLastIndex
import tour.donnees.catalog.extansion.isLastItemVisible
import tour.donnees.catalog.extansion.showIf
import tour.donnees.catalog.extansion.toObserve
import tour.donnees.catalog.view.adapter.TvShowDetailAdapter
import tour.donnees.catalog.view.adapter.TvShowEpisodeAdapter
import tour.donnees.catalog.viewmodel.CatalogViewModel

class TvShowDetailFragment : Fragment() {

    private lateinit var binding: FragmentTvShowDetailBinding

    private val viewModel by activityViewModel<CatalogViewModel>()

    private val detailAdapter = TvShowDetailAdapter()
    private val episodeAdapter = TvShowEpisodeAdapter()

    private val args by navArgs<TvShowDetailFragmentArgs>()

    private val concatAdapter by lazy { ConcatAdapter(detailAdapter, episodeAdapter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowDetailBinding.inflate(inflater)

        with(binding.tvShowSearchedList){
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
            endlessScrolling(this)
        }

        return binding.root
    }

    private fun initObservables() {
        toObserve(viewModel.isLoading) {
            binding.progressBar.showIf(it)
        }

        toObserve(viewModel.episodes) {
            episodeAdapter.updateAdapter(it)
            viewModel.notLoading()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        val show = args.show
        detailAdapter.updateAdapter(listOf(show))
        viewModel.getEpisodeByShowId(show.id)
    }

    private fun endlessScrolling(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (viewModel.isLoadingInProgress()) {
                    if (recyclerView.layoutManager.isLastItemVisible(episodeAdapter.getLastIndex())) {
                        viewModel.loadMoreEpisode()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cleanEpisodePagination()
    }

    override fun onDetach() {
        super.onDetach()
    }
}