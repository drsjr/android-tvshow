package tour.donnees.data.tvmaze.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class ShowDTO(
    @SerializedName("averageRuntime")
    val averageRuntime: Int? = 0,
    @SerializedName("dvdCountry")
    val dvdCountry: Any? = Any(),
    @SerializedName("ended")
    val ended: String? = "",
    @SerializedName("externals")
    val externals: ExternalsDTO? = ExternalsDTO(),
    @SerializedName("genres")
    val genres: List<String>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: ImageDTO? = ImageDTO(),
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("_links")
    val links: LinksDTO? = LinksDTO(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("network")
    val network: NetworkDTO? = NetworkDTO(),
    @SerializedName("officialSite")
    val officialSite: String? = "",
    @SerializedName("premiered")
    val premiered: String? = "",
    @SerializedName("rating")
    val rating: RatingDTO? = RatingDTO(),
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("schedule")
    val schedule: ScheduleDTO? = ScheduleDTO(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("summary")
    val summary: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("updated")
    val updated: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("webChannel")
    val webChannel: WebChannelDTO? = WebChannelDTO(),
    @SerializedName("weight")
    val weight: Int? = 0
)