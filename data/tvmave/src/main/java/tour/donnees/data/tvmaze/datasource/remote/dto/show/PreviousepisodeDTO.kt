package tour.donnees.data.tvmaze.datasource.remote.dto.show


import com.google.gson.annotations.SerializedName

data class PreviousepisodeDTO(
    @SerializedName("href")
    val href: String? = ""
)