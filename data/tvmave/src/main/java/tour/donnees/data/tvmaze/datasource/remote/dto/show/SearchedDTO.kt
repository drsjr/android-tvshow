package tour.donnees.data.tvmaze.datasource.remote.dto.show

import com.google.gson.annotations.SerializedName

data class SearchedDTO(
    @SerializedName("score")
    val score: Double? = 0.0,
    @SerializedName("show")
    val show: ShowDTO? = ShowDTO()
)