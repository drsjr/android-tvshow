package tour.donnees.data.tvmaze.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tour.donnees.data.tvmaze.datasource.remote.api.TvMazeApi
import tour.donnees.data.tvmaze.datasource.remote.dto.episode.EpisodeDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO

class RemoteDataSource(
    private val api: TvMazeApi
) {
    suspend fun getShowsByPage(page: Int): Flow<Result<Collection<ShowDTO>>> = flow {
        try {
            api.getShowByPages(page).apply {
                emit(Result.success(this))
            }
        } catch (e: HttpException) {
            emit(Result.failure(e))
        }
    }

    suspend fun getShowBySearch(searchText: String): Flow<Result<Collection<ShowDTO>>> = flow {
        try {
            api.getShowBySearch(searchText).apply {
                val collection: Collection<ShowDTO> = this.toList().map { searchedDTO ->
                    searchedDTO.show ?: ShowDTO()
                }.filter {
                    it.name != null && it.name.isNotBlank()
                }
                emit(Result.success(collection))
            }
        } catch (e: HttpException) {
            emit(Result.failure(e))
        }
    }

    suspend fun getEpisodeByShowId(showId: Int): Flow<Result<Collection<EpisodeDTO>>> = flow {
        try {
            api.getEpisodeByShowId(showId).apply {
                emit(Result.success(this))
            }
        } catch (e: HttpException) {
            emit(Result.failure(e))
        }
    }
}