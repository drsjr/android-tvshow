package tour.donnees.domain.tvmaze.di

import org.koin.dsl.module
import tour.donnees.data.tvmaze.di.TvShowDataModule
import tour.donnees.domain.tvmaze.usecase.*

val TvShowDomainModule = module {
    includes(TvShowDataModule)
    factory<GetShowListByPageUseCase> { GetShowListByPageUseCaseImpl(get()) }
    factory<GetShowListBySearchUseCase> { GetShowListBySearchUseCaseImpl(get()) }
    factory<GetEpisodeByShowIdUseCase> { GetEpisodeByShowIdUseCaseImpl(get()) }
}