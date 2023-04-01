package tour.donnees.data.tvmaze.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import tour.donnees.data.tvmaze.base.TestSuit
import tour.donnees.data.tvmaze.datasource.remote.RemoteDataSource

@OptIn(ExperimentalCoroutinesApi::class)
class TvShowRepositoryImplTest: TestSuit() {


    private val dataSource = RemoteDataSource(getAPI())

    private val repositoryImpl = TvShowRepositoryImpl(dataSource)

    @Test
    fun `Text getShowsByPages from TvShowRepository with Success`() {

        mockResponse(SHOWS_HTTP_200, 200)

        runTest {
            repositoryImpl.getShowsByPages(1).collect { result ->
                result.getOrNull()?.apply {
                    Assert.assertEquals(2, this.size)
                    Assert.assertEquals("Kirby Buckets", this.toList()[0].name)
                    Assert.assertEquals("Downton Abbey", this.toList()[1].name)
                }
            }
        }
    }

    @Test
    fun `Text getShowsByPages from TvShowRepository with Error`() {

        mockResponse(SHOWS_HTTP_200, 429)

        runTest {
            repositoryImpl.getShowsByPages(1).collect { result ->
                Assert.assertThrows(HttpException::class.java, result::getOrThrow)
            }
        }
    }
}