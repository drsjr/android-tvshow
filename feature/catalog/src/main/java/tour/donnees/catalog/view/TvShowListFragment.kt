package tour.donnees.catalog.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import tour.donnees.catalog.R
import tour.donnees.catalog.databinding.FragmentTvShowListBinding
import tour.donnees.catalog.extansion.getLastIndex
import tour.donnees.catalog.extansion.isLastItemVisible
import tour.donnees.catalog.extansion.showIf
import tour.donnees.catalog.extansion.toObserve
import tour.donnees.catalog.view.adapter.TvShowAdapter
import tour.donnees.catalog.viewmodel.CatalogViewModel
import tour.donnees.domain.tvmaze.model.Show

class TvShowListFragment : Fragment() {

    private lateinit var binding: FragmentTvShowListBinding

    private val viewModel by activityViewModel<CatalogViewModel>()

    private lateinit var showAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowListBinding.inflate(inflater)

        with(binding.tvShowList){
            layoutManager = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(context, 3)
                else -> GridLayoutManager(context, 2)
            }
            showAdapter = TvShowAdapter(::navigateToDetail)
            adapter = showAdapter
            addDivider()
            endlessScrolling(this)
        }

        return binding.root
    }

    private fun addDivider() {
        binding.tvShowList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.tvShowList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))

    }

    private fun initObservables() {
        toObserve(viewModel.shows) {
            showAdapter.updateAdapter(it)
            viewModel.notLoading()
        }
        toObserve(viewModel.isLoading) {
            binding.progressBar.showIf(it)
        }
        toObserve(viewModel.searched) {
            showAdapter.updateAdapter(it)
            viewModel.notLoading()
        }

    }

    private fun navigateToDetail(show: Show) {
        val bundle = Bundle()
        bundle.putParcelable("show", show)
        findNavController().navigate(R.id.action_tvShowListCatalog_to_tvShowDetail, bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        if (savedInstanceState == null) viewModel.loadMoreTvShow()
    }

    private fun endlessScrolling(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (viewModel.isLoadingInProgress() && isIconified()) {
                    if (recyclerView.layoutManager.isLastItemVisible(showAdapter.getLastIndex())) {
                        viewModel.loadMoreTvShow()
                    }
                }
            }
        })
    }

    private fun isIconified(): Boolean {
        return (activity?.let {
            it as CatalogActivity
            it.isIconified()
        } ?: false)
    }

    override fun onDetach() {
        super.onDetach()
    }
}