package tour.donnees.data.tvmaze.di

import org.koin.dsl.module
import tour.donnees.data.tvmaze.datasource.remote.RemoteDataSource
import tour.donnees.data.tvmaze.network.NetworkModule
import tour.donnees.data.tvmaze.repository.TvShowRepository
import tour.donnees.data.tvmaze.repository.TvShowRepositoryImpl

val TvShowDataModule = module {
    includes(NetworkModule)
    factory { RemoteDataSource(get()) }
    factory<TvShowRepository> { TvShowRepositoryImpl(get()) }
}