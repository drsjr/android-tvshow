package tour.donnees.data.tvmaze.datasource.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import tour.donnees.data.tvmaze.datasource.remote.dto.SearchedDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.ShowDTO

interface TvMazeApi {

    @GET("/shows")
    suspend fun getShowByPages(
        @Query("page") page: Int
    ): Collection<ShowDTO>

    @GET("/search/shows")
    suspend fun getShowBySearch(
        @Query("q") searchText: String
    ): Collection<SearchedDTO>

}