package tour.donnees.data.tvmaze.datasource

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import tour.donnees.data.tvmaze.base.TestSuit
import tour.donnees.data.tvmaze.datasource.remote.RemoteDataSource
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceTest: TestSuit() {

    private val dataSource = RemoteDataSource(getAPI())

    @Test
    fun `Test getShowByPage from RemoteDataSource with SuccessTest`() {

        mockResponse(SHOWS_HTTP_200, 200)

        runTest {
            dataSource.getShowsByPage(1).collect { result ->
                result.getOrNull()?.apply {
                    Assert.assertEquals(2, this.size)
                    Assert.assertEquals("Kirby Buckets", this.toList()[0].name)
                    Assert.assertEquals("Downton Abbey", this.toList()[1].name)
                }
            }
        }
    }

    @Test
    fun `Test getShowByPage from RemoteDataSource with Error`() {

        mockResponse(SHOWS_HTTP_200, 429)

        runTest {
            dataSource.getShowsByPage(1).collect { result ->
                Assert.assertThrows(HttpException::class.java, result::getOrThrow)
            }
        }
    }


    @Test
    fun `Test getShowBySearch from RemoteDataSource with Success`() {

        mockResponse(SEARCH_HTTP_200, 200)

        runTest {
            dataSource.getShowBySearch("test").collect { result ->
                result.getOrNull()?.apply {
                    Assert.assertEquals(10, this.size)
                }
            }
        }
    }

    @Test
    fun `Test getShowBySearch from RemoteDataSource with Error`() {

        mockResponse(SEARCH_HTTP_200, 429)

        runTest {
            dataSource.getShowBySearch("test").collect { result ->
                Assert.assertThrows(HttpException::class.java, result::getOrThrow)
            }
        }
    }

}