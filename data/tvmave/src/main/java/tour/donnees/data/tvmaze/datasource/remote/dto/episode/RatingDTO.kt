package tour.donnees.data.tvmaze.datasource.remote.dto.episode


import com.google.gson.annotations.SerializedName

data class RatingDTO(
    @SerializedName("average")
    val average: Double? = 0.0
)