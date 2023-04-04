package tour.donnees.domain.tvmaze.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import tour.donnees.data.tvmaze.datasource.remote.dto.episode.EpisodeDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO
import tour.donnees.data.tvmaze.repository.TvShowRepository
import tour.donnees.domain.tvmaze.model.Episode
import tour.donnees.domain.tvmaze.model.Show
import tour.donnees.domain.tvmaze.usecase.GetEpisodeByShowIdUseCaseImpl.Companion.REGULAR
import java.lang.Exception

@ExperimentalCoroutinesApi
class GetEpisodeByShowIdUseCaseTest {

    private val repository: TvShowRepository = mockk()

    private val useCase = GetEpisodeByShowIdUseCaseImpl(repository)

    @Test
    fun `Test Use Case and call repository return Success`() {
        coEvery { repository.getEpisodeByShowId(any()) } returns flow {
            emit(
                Result.success(
                    listOf(
                        EpisodeDTO(
                            name = "Test",
                            type = REGULAR
                        ),
                        EpisodeDTO(
                            name = "Test 2"
                        )
                    )
                )
            )
        }

        runTest {
            useCase.invoke(1).collect { result ->
                result.fold({
                    Assert.assertEquals(1, it.size)
                    Assert.assertEquals("Test", it.toList()[0].name)
                    Assert.assertEquals(Episode::class.java, it.toList()[0].javaClass)
                }, {})
            }
        }
    }

    @Test
    fun `Test Use Case and call repository return Error`() {
        coEvery { repository.getEpisodeByShowId(any()) } returns flow {
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