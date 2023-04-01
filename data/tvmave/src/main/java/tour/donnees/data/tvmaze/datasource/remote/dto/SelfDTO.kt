package tour.donnees.data.tvmaze.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class SelfDTO(
    @SerializedName("href")
    val href: String? = ""
)