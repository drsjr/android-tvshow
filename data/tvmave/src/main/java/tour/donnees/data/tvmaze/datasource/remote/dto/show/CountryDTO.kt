package tour.donnees.data.tvmaze.datasource.remote.dto.show


import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("timezone")
    val timezone: String? = ""
)