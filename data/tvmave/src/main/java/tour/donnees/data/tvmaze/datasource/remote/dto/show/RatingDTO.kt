package tour.donnees.data.tvmaze.datasource.remote.dto.show


import com.google.gson.annotations.SerializedName

data class RatingDTO(
    @SerializedName("average")
    val average: Float = 0F
)