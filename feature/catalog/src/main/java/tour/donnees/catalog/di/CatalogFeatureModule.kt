package tour.donnees.catalog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tour.donnees.catalog.viewmodel.CatalogViewModel
import tour.donnees.data.tvmaze.di.TvShowDataModule

val CatalogFeatureModule = module {
    includes(TvShowDataModule)
    viewModel { CatalogViewModel(get()) }
}