package tour.donnees.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tour.donnees.catalog.util.Pagination
import tour.donnees.domain.tvmaze.model.Episode
import tour.donnees.domain.tvmaze.model.Show
import tour.donnees.domain.tvmaze.usecase.GetEpisodeByShowIdUseCase
import tour.donnees.domain.tvmaze.usecase.GetShowListByPageUseCase
import tour.donnees.domain.tvmaze.usecase.GetShowListBySearchUseCase

class CatalogViewModel(
    private val getShowListByPageUseCase: GetShowListByPageUseCase,
    private val getShowListBySearchUseCase: GetShowListBySearchUseCase,
    private val getEpisodeByShowIdUseCase: GetEpisodeByShowIdUseCase,
    private val showPagination: Pagination<Show>,
    private val episodePagination: Pagination<Episode>
): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading as LiveData<Boolean>

    private val _showCollection = MutableLiveData<List<Show>>()
    val shows = _showCollection as LiveData<List<Show>>

    private val _searchedCollection = MutableLiveData<List<Show>>()
    val searched = _searchedCollection as LiveData<List<Show>>

    private val _episodeCollection = MutableLiveData<List<Episode>>()
    val episodes = _episodeCollection as LiveData<List<Episode>>

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable -> }

    private fun getTvShowCatalogByPage() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getShowListByPageUseCase(showPagination.nextPage()).collect { result ->
                result.fold(::loadTvShow) {
                    it.message
                }
            }
        }
    }

    fun getEpisodeByShowId(showId: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getEpisodeByShowIdUseCase(showId).collect { result ->
                result.fold(::loadEpisode) {
                    it.message
                }
            }
        }
    }

    fun getTvShowBySearch(searchText: String?) {
        searchText?.let {
            if (it.length < 4) return@let
            isLoading()
            viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                getShowListBySearchUseCase(it).collect { result ->
                    result.fold(::loadTvShowSearched) {
                        it.message
                    }
                }
            }
        }
    }

    private fun loadTvShow(tvShows: Collection<Show>) {
        showPagination.addAll(tvShows)
        loadMoreTvShow()
    }

    private fun loadEpisode(episodes: Collection<Episode>) {
        episodePagination.addAll(episodes)
        loadMoreEpisode()
    }

    private fun loadTvShowSearched(tvShows: Collection<Show>) {
        _searchedCollection.postValue(tvShows.toList())
    }

    fun loadMoreTvShow() {
        isLoading()
        if (showPagination.hasMore()) {
            _showCollection.postValue(showPagination.nextItems().toList())
        } else {
            getTvShowCatalogByPage()
        }
    }

    fun loadMoreEpisode() {
        isLoading()
        if (episodePagination.hasMore()) {
            _episodeCollection.postValue(episodePagination.nextItems().toList())
        } else {
            notLoading()
        }
    }

    fun isLoadingInProgress() = (_isLoading.value?.not() ?: false)
    fun isLoading() = _isLoading.postValue(true)
    fun notLoading() = _isLoading.postValue(false)
    fun cleanEpisodePagination() {
        episodePagination.clear()
        _episodeCollection.postValue(emptyList())
    }

}