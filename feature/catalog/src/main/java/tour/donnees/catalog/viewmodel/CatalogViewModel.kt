package tour.donnees.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tour.donnees.catalog.util.Pagination
import tour.donnees.data.tvmaze.datasource.remote.dto.SearchedDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository

class CatalogViewModel(
    private val tvShowRepository: TvShowRepository,
    private val pagination: Pagination<ShowDTO>
): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading as LiveData<Boolean>

    private val _collection = MutableLiveData<List<ShowDTO>>()
    val collection = _collection as LiveData<List<ShowDTO>>

    private val _searchedCollection = MutableLiveData<List<ShowDTO>>()
    val searchedCollection = _searchedCollection as LiveData<List<ShowDTO>>

    private fun getTvShowCatalogByPage() {
        viewModelScope.launch(Dispatchers.IO) {
            tvShowRepository.getShowsByPages(pagination.nextPage()).collect { result ->
                result.fold(::loadTvShow) {
                    it.message
                }
            }
        }
    }

    fun getTvShowBySearch(searchText: String?) {
        searchText?.let {
            if (it.length < 4) return@let
            isLoading()
            viewModelScope.launch(Dispatchers.IO) {
                tvShowRepository.getShowBySearch(searchText).collect { result ->
                    result.fold(::loadTvShowSearched) {
                        it.message
                    }
                }
            }
        }

    }

    private fun loadTvShow(tvShows: Collection<ShowDTO>) {
        pagination.addAll(tvShows)
        loadMoreTvShow()
    }

    private fun loadTvShowSearched(tvShows: Collection<ShowDTO>) {
        _searchedCollection.postValue(tvShows.toList())
    }

    fun loadMoreTvShow() {
        isLoading()
        if (pagination.hasMore()) {
            _collection.postValue(pagination.nextItems().toList())
        } else {
            getTvShowCatalogByPage()
        }
    }

    fun isLoadingInProgress() = (_isLoading.value?.not() ?: false)

    fun isLoading() = _isLoading.postValue(true)

    fun notLoading() = _isLoading.postValue(false)

}