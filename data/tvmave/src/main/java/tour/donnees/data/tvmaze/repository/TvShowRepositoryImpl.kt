package tour.donnees.data.tvmaze.repository

import kotlinx.coroutines.flow.Flow
import tour.donnees.data.tvmaze.datasource.remote.RemoteDataSource
import tour.donnees.data.tvmaze.datasource.remote.dto.episode.EpisodeDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO

class TvShowRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): TvShowRepository {
    override suspend fun getShowsByPages(page: Int): Flow<Result<Collection<ShowDTO>>> {
        return remoteDataSource.getShowsByPage(page)
    }

    override suspend fun getShowBySearch(searchText: String): Flow<Result<Collection<ShowDTO>>> {
        return remoteDataSource.getShowBySearch(searchText)
    }

    override suspend fun getEpisodeByShowId(showId: Int): Flow<Result<Collection<EpisodeDTO>>> {
        return remoteDataSource.getEpisodeByShowId(showId)
    }
}