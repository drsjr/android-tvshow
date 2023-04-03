package tour.donnees.data.tvmaze.datasource.remote.dto.episode


import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    @SerializedName("airdate")
    val airdate: String? = "",
    @SerializedName("airstamp")
    val airstamp: String? = "",
    @SerializedName("airtime")
    val airtime: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: ImageDTO? = ImageDTO(),
    @SerializedName("_links")
    val links: LinksDTO? = LinksDTO(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("number")
    val number: Int? = 0,
    @SerializedName("rating")
    val rating: RatingDTO? = RatingDTO(),
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("season")
    val season: Int? = 0,
    @SerializedName("summary")
    val summary: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("url")
    val url: String? = ""
)