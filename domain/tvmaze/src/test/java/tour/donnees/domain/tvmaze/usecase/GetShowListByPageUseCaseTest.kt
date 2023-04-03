package tour.donnees.domain.tvmaze.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository
import tour.donnees.domain.tvmaze.model.Show

import java.lang.Exception

@ExperimentalCoroutinesApi
class GetShowListByPageUseCaseTest {

    private val repository: TvShowRepository = mockk()

    private val useCase = GetShowListByPageUseCaseImpl(repository)

    @Test
    fun `Test Use Case and call repository return Success`() {
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
            useCase.invoke(1).collect { result ->
                result.fold({
                    Assert.assertEquals(1, it.size)
                    Assert.assertEquals("Test", it.toList()[0].name)
                    Assert.assertEquals(Show::class.java, it.toList()[0].javaClass)
                }, {})
            }
        }
    }

    @Test
    fun `Test Use Case and call repository return Error`() {
        coEvery { repository.getShowsByPages(any()) } returns flow {
            emit(
                Result.failure(Exception("Error aaa!!!"))
            )
        }

        runTest {
            useCase.invoke(1).collect { result ->
                Assert.assertThrows(Exception::class.java, result::getOrThrow)
                result.fold({}, {
                    Assert.assertEquals("Error aaa!!!", it.message)
                })
            }
        }
    }

}