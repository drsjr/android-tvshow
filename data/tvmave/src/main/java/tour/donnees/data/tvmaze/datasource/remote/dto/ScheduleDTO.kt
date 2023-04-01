package tour.donnees.data.tvmaze.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class ScheduleDTO(
    @SerializedName("days")
    val days: List<String?>? = listOf(),
    @SerializedName("time")
    val time: String? = ""
)