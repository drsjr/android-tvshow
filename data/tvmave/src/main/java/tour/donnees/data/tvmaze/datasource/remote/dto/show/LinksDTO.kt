package tour.donnees.data.tvmaze.datasource.remote.dto.show


import com.google.gson.annotations.SerializedName

data class LinksDTO(
    @SerializedName("previousepisode")
    val previousepisode: PreviousepisodeDTO? = PreviousepisodeDTO(),
    @SerializedName("self")
    val self: SelfDTO? = SelfDTO()
)