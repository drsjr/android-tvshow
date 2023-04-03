package tour.donnees.data.tvmaze.datasource.remote.dto.episode


import com.google.gson.annotations.SerializedName

data class LinksDTO(
    @SerializedName("self")
    val self: SelfDTO? = SelfDTO(),
    @SerializedName("show")
    val show: ShowDTO? = ShowDTO()
)