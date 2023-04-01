package tour.donnees.data.tvmaze.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class NetworkDTO(
    @SerializedName("country")
    val country: CountryDTO? = CountryDTO(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("officialSite")
    val officialSite: String? = ""
)