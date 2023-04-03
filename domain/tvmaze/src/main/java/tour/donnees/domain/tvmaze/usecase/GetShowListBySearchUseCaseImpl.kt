package tour.donnees.domain.tvmaze.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository
import tour.donnees.domain.tvmaze.model.Show
import tour.donnees.domain.tvmaze.model.mapTo

class GetShowListBySearchUseCaseImpl(
    private val repository: TvShowRepository
): GetShowListBySearchUseCase {

    override fun invoke(param: String): Flow<Result<Collection<Show>>> = flow {
        repository.getShowBySearch(param).collect { result ->
            result.fold({
                emit(success(it))
            }, {
                emit(Result.failure(it))
            })
        }
    }

    private fun success(collection: Collection<ShowDTO>): Result<Collection<Show>> {
        return Result.success(
            collection.map { it.mapTo() }
        )
    }
}