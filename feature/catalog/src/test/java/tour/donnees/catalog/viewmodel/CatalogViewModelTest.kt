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
import tour.donnees.catalog.util.Pagination
import tour.donnees.catalog.util.PaginationImpl
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository


@ExperimentalCoroutinesApi
class CatalogViewModelTest {

    @get:Rule
    val dispatcherRule = CoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: TvShowRepository = mockk()
    private val pagination: Pagination<ShowDTO> = PaginationImpl()
    private val viewModel = CatalogViewModel(repository, pagination)


    @Test
    fun `Test loadMoreTvShow and call repository return Success`() {
        coEvery { repository.getShowsByPages(any()) } returns flow {
            emit(
                Result.success(
                    listOf(
                        ShowDTO(name = "Test")
                    )
                )
            )
        }

        runTest {
            viewModel.loadMoreTvShow()
            Assert.assertEquals(1, viewModel.collection.getOrAwaitValue().size)
            coVerify { repository.getShowsByPages(any()) }
        }
    }

    @Test
    fun `Test loadMoreTvShow and don't call repository return Success`() {
        coEvery { repository.getShowsByPages(any()) } returns flow {
            emit(Result.success(getShowDTOList()))
        }

        runTest {
            viewModel.loadMoreTvShow()

            Assert.assertEquals(10, viewModel.collection.getOrAwaitValue().size)

            viewModel.loadMoreTvShow()

            Assert.assertEquals(20, viewModel.collection.getOrAwaitValue().size)

            coVerify(atMost = 1) { repository.getShowsByPages(any()) }
        }
    }

    @Test
    fun `Test getTvShowBySearch and call repository just once return Success`() {
        coEvery { repository.getShowBySearch(any()) } returns flow {
            emit(Result.success(getShowDTOList().toList().subList(0, 10)))
        }

        runTest {
            viewModel.getTvShowBySearch("te")
            viewModel.getTvShowBySearch("tes")
            viewModel.getTvShowBySearch("test")

            Assert.assertEquals(10, viewModel.searchedCollection.getOrAwaitValue().size)
            coVerify(atMost = 1) { repository.getShowBySearch(any()) }
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