package tour.donnees.data.tvmaze.datasource.remote.dto.show


import com.google.gson.annotations.SerializedName

data class ExternalsDTO(
    @SerializedName("imdb")
    val imdb: String? = "",
    @SerializedName("thetvdb")
    val thetvdb: Int? = 0,
    @SerializedName("tvrage")
    val tvrage: Int? = 0
)