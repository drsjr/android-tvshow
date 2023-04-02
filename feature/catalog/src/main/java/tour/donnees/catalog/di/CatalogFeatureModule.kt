package tour.donnees.catalog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tour.donnees.catalog.model.Pagination
import tour.donnees.catalog.model.PaginationImpl
import tour.donnees.catalog.viewmodel.CatalogViewModel
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO
import tour.donnees.data.tvmaze.di.TvShowDataModule

val CatalogFeatureModule = module {
    includes(TvShowDataModule)

    single<Pagination<ShowDTO>> { PaginationImpl() }

    viewModel { CatalogViewModel(get(), get()) }
}