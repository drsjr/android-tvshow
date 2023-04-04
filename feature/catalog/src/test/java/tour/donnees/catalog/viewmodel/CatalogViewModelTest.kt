package tour.donnees.catalog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import tour.donnees.catalog.base.CoroutineDispatcher
import tour.donnees.catalog.base.getOrAwaitValue
import tour.donnees.catalog.util.EpisodePaginationImpl
import tour.donnees.catalog.util.Pagination
import tour.donnees.catalog.util.ShowPaginationImpl
import tour.donnees.data.tvmaze.datasource.remote.dto.episode.EpisodeDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO
import tour.donnees.domain.tvmaze.model.Episode
import tour.donnees.domain.tvmaze.model.Show
import tour.donnees.domain.tvmaze.model.mapTo
import tour.donnees.domain.tvmaze.usecase.GetEpisodeByShowIdUseCase
import tour.donnees.domain.tvmaze.usecase.GetShowListByPageUseCase
import tour.donnees.domain.tvmaze.usecase.GetShowListBySearchUseCase


@ExperimentalCoroutinesApi
class CatalogViewModelTest {

    @get:Rule
    val dispatcherRule = CoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private val getShowListBySearchUseCase: GetShowListBySearchUseCase = mockk()
    private val getShowListByPageUseCase: GetShowListByPageUseCase = mockk()
    private val getEpisodeByShowIdUseCase: GetEpisodeByShowIdUseCase = mockk()
    private val showPagination: Pagination<Show> = ShowPaginationImpl()
    private val episodePagination: Pagination<Episode> = EpisodePaginationImpl()

    private val viewModel = CatalogViewModel(
        getShowListByPageUseCase,
        getShowListBySearchUseCase,
        getEpisodeByShowIdUseCase,
        showPagination,
        episodePagination
    )

    @Test
    fun `Test loadMoreTvShow and call Use Case return Success`() {
        coEvery { getShowListByPageUseCase(any()) } returns flow {
            emit(
                Result.success(
                    listOf(
                        ShowDTO(name = "Test").mapTo()
                    )
                )
            )
        }

        runTest {
            viewModel.loadMoreTvShow()
            Assert.assertEquals(1, viewModel.shows.getOrAwaitValue().size)
            coVerify { getShowListByPageUseCase(any()) }
        }
    }

    @Test
    fun `Test loadMoreTvShow and don't call Use Case return Success`() {
        coEvery { getShowListByPageUseCase(any()) } returns flow {
            emit(Result.success(getShowDTOList().map { it.mapTo() }))
        }

        runTest {
            viewModel.loadMoreTvShow()

            Assert.assertEquals(10, viewModel.shows.getOrAwaitValue().size)

            viewModel.loadMoreTvShow()

            Assert.assertEquals(20, viewModel.shows.getOrAwaitValue().size)

            coVerify(atMost = 1) { getShowListByPageUseCase(any()) }
        }
    }

    @Test
    fun `Test getTvShowBySearch and call Use Case just once return Success`() {
        coEvery { getShowListBySearchUseCase(any()) } returns flow {
            emit(Result.success(getShowDTOList().toList().subList(0, 10).map { it.mapTo() }))
        }

        runTest {
            viewModel.getTvShowBySearch("te")
            viewModel.getTvShowBySearch("tes")
            viewModel.getTvShowBySearch("test")

            Assert.assertEquals(10, viewModel.searched.getOrAwaitValue().size)
            coVerify(atMost = 1) { getShowListBySearchUseCase(any()) }
        }
    }

    @Test
    fun `Test loadEpisode calling Use Case and return Success`() {
        coEvery { getEpisodeByShowIdUseCase(any()) } returns flow {
            emit(
                Result.success(
                    listOf(
                        EpisodeDTO(name = "Test").mapTo()
                    )
                )
            )
        }

        runTest {
            viewModel.getEpisodeByShowId(1)
            Assert.assertEquals(1, viewModel.episodes.getOrAwaitValue().size)
            coVerify { getEpisodeByShowIdUseCase(any()) }
        }
    }



    @Test
    fun `Test ShouldLoadMore return False`() {
        viewModel.notLoading()
        Assert.assertFalse(viewModel.isLoading.getOrAwaitValue())

    }

    @Test
    fun `Test ShouldLoadMore return True`() {
        viewModel.isLoading()
        Assert.assertTrue(viewModel.isLoading.getOrAwaitValue())
    }

    private fun getShowDTOList(): Collection<ShowDTO> {
        val list = mutableListOf<ShowDTO>()

        for (item in 1..30) {
            list.add(ShowDTO(name = "Test $item"))
        }

        return list
    }

}