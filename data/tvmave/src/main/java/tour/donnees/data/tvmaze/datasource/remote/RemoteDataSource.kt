package tour.donnees.data.tvmaze.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tour.donnees.data.tvmaze.datasource.remote.api.TvMazeApi
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO

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
}