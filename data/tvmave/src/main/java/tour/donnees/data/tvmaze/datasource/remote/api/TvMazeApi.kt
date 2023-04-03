package tour.donnees.data.tvmaze.datasource.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tour.donnees.data.tvmaze.datasource.remote.dto.show.SearchedDTO
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO

interface TvMazeApi {

    @GET("/shows")
    suspend fun getShowByPages(
        @Query("page") page: Int
    ): Collection<ShowDTO>

    @GET("/search/shows")
    suspend fun getShowBySearch(
        @Query("q") searchText: String
    ): Collection<SearchedDTO>

    @GET("shows/{showId}/episodes")
    suspend fun getEpisodeByShowId(
        @Path("showId") showId: Int
    ): Collection<Any>

}