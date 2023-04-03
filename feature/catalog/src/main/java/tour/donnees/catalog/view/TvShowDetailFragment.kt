package tour.donnees.catalog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import tour.donnees.catalog.databinding.FragmentTvShowDetailBinding
import tour.donnees.catalog.extansion.toObserve
import tour.donnees.catalog.view.adapter.TvShowAdapter
import tour.donnees.catalog.view.adapter.TvShowDetailAdapter
import tour.donnees.catalog.viewmodel.CatalogViewModel

class TvShowDetailFragment : Fragment() {

    private lateinit var binding: FragmentTvShowDetailBinding

    private val viewModel by activityViewModel<CatalogViewModel>()

    private val detailAdapter = TvShowDetailAdapter()

    private val args by navArgs<TvShowDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowDetailBinding.inflate(inflater)

        with(binding.tvShowSearchedList){
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
        }

        return binding.root
    }

    private fun initObservables() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val show = args.show

        detailAdapter.updateAdapter(listOf(show))
    }
}