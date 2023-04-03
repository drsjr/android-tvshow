package tour.donnees.data.tvmaze.repository

import kotlinx.coroutines.flow.Flow
import tour.donnees.data.tvmaze.datasource.remote.dto.SearchedDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO

interface TvShowRepository {
    suspend fun getShowsByPages(page: Int): Flow<Result<Collection<ShowDTO>>>
    suspend fun getShowBySearch(searchText: String): Flow<Result<Collection<ShowDTO>>>
}