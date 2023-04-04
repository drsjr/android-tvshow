package tour.donnees.domain.tvmaze.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tour.donnees.data.tvmaze.datasource.remote.dto.episode.EpisodeDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository
import tour.donnees.domain.tvmaze.model.Episode
import tour.donnees.domain.tvmaze.model.mapTo

class GetEpisodeByShowIdUseCaseImpl(
    private val repository: TvShowRepository
): GetEpisodeByShowIdUseCase {

    override fun invoke(param: Int): Flow<Result<Collection<Episode>>> = flow {
        repository.getEpisodeByShowId(param).collect { result ->
            result.fold({
                emit(success(it))
            }, {
                emit(Result.failure(it))
            })
        }
    }

    private fun success(collection: Collection<EpisodeDTO>): Result<Collection<Episode>> {
        val result = collection
            .filter { it.type == REGULAR }
            .map { it.mapTo() }

        return Result.success(result)
    }

    companion object {
        const val REGULAR = "regular"
    }
}