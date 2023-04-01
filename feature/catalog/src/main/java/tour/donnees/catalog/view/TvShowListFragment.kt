package tour.donnees.catalog.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import tour.donnees.catalog.databinding.FragmentTvShowListBinding
import tour.donnees.catalog.viewmodel.CatalogViewModel

class TvShowListFragment : Fragment() {

    private lateinit var binding: FragmentTvShowListBinding

    private val viewModel by activityViewModel<CatalogViewModel>()

    private val showAdapter = TvShowAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowListBinding.inflate(inflater)

        with(binding.tvShowList){
            layoutManager = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE ->
                    GridLayoutManager(context, 3)
                else ->
                    GridLayoutManager(context, 2)
            }

            adapter = showAdapter
            endlessScrolling(this)
        }

        return binding.root
    }

    private fun initObservables() {
        viewModel.collection.observe(viewLifecycleOwner) {
            showAdapter.updateAdapter(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        if (savedInstanceState == null) viewModel.getTvShowCatalogByPage()
    }

    private fun endlessScrolling(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == showAdapter.itemCount - 1) {
                    if (/*!viewModel.isLoading() && viewModel.hasMorePages()*/viewModel.pagination.hasMore()) {
                        viewModel.loadMoreTvShow()
                    } else {
                        viewModel.getTvShowCatalogByPage()
                    }
                }
            }
        })
    }
}