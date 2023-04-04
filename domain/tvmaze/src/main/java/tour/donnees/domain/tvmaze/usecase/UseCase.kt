package tour.donnees.domain.tvmaze.usecase

import kotlinx.coroutines.flow.Flow
import tour.donnees.domain.tvmaze.model.Episode
import tour.donnees.domain.tvmaze.model.Show

interface UseCase<P, R> {
    operator fun invoke(param: P):  Flow<Result<R>>
}

interface GetShowListByPageUseCase: UseCase<Int, Collection<Show>>
interface GetShowListBySearchUseCase: UseCase<String, Collection<Show>>
interface GetEpisodeByShowIdUseCase: UseCase<Int, Collection<Episode>>