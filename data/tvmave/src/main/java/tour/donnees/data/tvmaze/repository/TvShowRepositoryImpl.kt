package tour.donnees.data.tvmaze.repository

import kotlinx.coroutines.flow.Flow
import tour.donnees.data.tvmaze.datasource.remote.RemoteDataSource
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO

class TvShowRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): TvShowRepository {
    override suspend fun getShowsByPages(page: Int): Flow<Result<Collection<ShowDTO>>> {
        return remoteDataSource.getShowsByPage(page)
    }
}