package tour.donnees.catalog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tour.donnees.catalog.util.Pagination
import tour.donnees.catalog.util.PaginationImpl
import tour.donnees.catalog.viewmodel.CatalogViewModel
import tour.donnees.data.tvmaze.di.TvShowDataModule
import tour.donnees.domain.tvmaze.di.TvShowDomainModule
import tour.donnees.domain.tvmaze.model.Show

val CatalogFeatureModule = module {
    includes(TvShowDataModule, TvShowDomainModule)

    single<Pagination<Show>> { PaginationImpl() }

    viewModel { CatalogViewModel(get(), get(), get()) }
}