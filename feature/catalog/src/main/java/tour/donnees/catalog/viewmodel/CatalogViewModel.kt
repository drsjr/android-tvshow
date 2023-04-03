package tour.donnees.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tour.donnees.catalog.util.Pagination
import tour.donnees.domain.tvmaze.model.Show
import tour.donnees.domain.tvmaze.usecase.GetShowListByPageUseCase
import tour.donnees.domain.tvmaze.usecase.GetShowListBySearchUseCase

class CatalogViewModel(
    private val getShowListByPageUseCase: GetShowListByPageUseCase,
    private val getShowListBySearchUseCase: GetShowListBySearchUseCase,
    private val pagination: Pagination<Show>
): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading as LiveData<Boolean>

    private val _collection = MutableLiveData<List<Show>>()
    val collection = _collection as LiveData<List<Show>>

    private val _searchedCollection = MutableLiveData<List<Show>>()
    val searchedCollection = _searchedCollection as LiveData<List<Show>>

    private fun getTvShowCatalogByPage() {
        viewModelScope.launch(Dispatchers.IO) {
            getShowListByPageUseCase(pagination.nextPage()).collect { result ->
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
                getShowListBySearchUseCase(it).collect { result ->
                    result.fold(::loadTvShowSearched) {
                        it.message
                    }
                }
            }
        }
    }

    private fun loadTvShow(tvShows: Collection<Show>) {
        pagination.addAll(tvShows)
        loadMoreTvShow()
    }

    private fun loadTvShowSearched(tvShows: Collection<Show>) {
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