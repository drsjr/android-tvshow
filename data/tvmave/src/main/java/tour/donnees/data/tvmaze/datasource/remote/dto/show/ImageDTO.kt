package tour.donnees.data.tvmaze.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class ImageDTO(
    @SerializedName("medium")
    val medium: String? = "",
    @SerializedName("original")
    val original: String? = ""
)