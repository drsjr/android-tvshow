package tour.donnees.data.tvmaze.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tour.donnees.data.tvmaze.datasource.remote.api.TvMazeApi

val NetworkModule = module {
    single { getOkHttpClient() }
    single { getRetrofit(get()) }
    single { get<Retrofit>().create(TvMazeApi::class.java) }
}

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
        })
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.tvmaze.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}