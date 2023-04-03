package tour.donnees.data.tvmaze.datasource.remote.dto.episode


import com.google.gson.annotations.SerializedName

data class ShowDTO(
    @SerializedName("href")
    val href: String? = ""
)