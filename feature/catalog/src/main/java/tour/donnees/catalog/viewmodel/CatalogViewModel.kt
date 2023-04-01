package tour.donnees.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tour.donnees.catalog.model.Pagination
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository

class CatalogViewModel(
    private val tvShowRepository: TvShowRepository
): ViewModel() {

    val pagination = Pagination()
    private val allShowList = mutableListOf<ShowDTO>()
    private val _collection = MutableLiveData<List<ShowDTO>>()
    val collection = _collection as LiveData<List<ShowDTO>>

    fun getTvShowCatalogByPage() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowRepository.getShowsByPages(pagination.loadNewPage()).collect { result ->
                result.fold(::loadTvShow) {
                    it.message
                }
            }
        }
    }

    private fun loadTvShow(tvShows: Collection<ShowDTO>) {
        allShowList.addAll(tvShows)
        pagination.addNewList(allShowList.size)
        loadMoreTvShow()
    }

    fun loadMoreTvShow() {
        val current = pagination.getItemPage()
        _collection.postValue(allShowList.subList(current, pagination.getMore()))
    }
}