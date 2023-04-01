package tour.donnees.data.tvmaze.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tour.donnees.data.tvmaze.datasource.remote.api.TvMazeApi
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
open class TestSuit {

    private val webServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api: TvMazeApi = Retrofit.Builder()
        .baseUrl(webServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TvMazeApi::class.java)

    fun getAPI(): TvMazeApi = api

    fun mockResponse(fileName: String, code: Int) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("$fileName.json")

        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            webServer.enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }

    companion object {
        const val SHOWS_HTTP_200 = "shows/response_200"
        const val SHOWS_HTTP_429 = "shows/response_429"
    }
}