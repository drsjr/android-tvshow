package tour.donnees.catalog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tour.donnees.catalog.util.EpisodePaginationImpl
import tour.donnees.catalog.util.Pagination
import tour.donnees.catalog.util.ShowPaginationImpl
import tour.donnees.catalog.viewmodel.CatalogViewModel
import tour.donnees.domain.tvmaze.di.TvShowDomainModule
import tour.donnees.domain.tvmaze.model.Episode
import tour.donnees.domain.tvmaze.model.Show

val CatalogFeatureModule = module {
    includes(TvShowDomainModule)

    factory<Pagination<Show>>(named("showPagination")) { ShowPaginationImpl() }
    factory<Pagination<Episode>>(named("episodePagination")) { EpisodePaginationImpl() }

    viewModel {
        CatalogViewModel(
            get(),
            get(),
            get(),
            get(qualifier = named("showPagination")),
            get(qualifier = named("episodePagination")))
    }
}