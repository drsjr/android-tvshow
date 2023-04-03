package tour.donnees.domain.tvmaze.model
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO
import java.io.Serializable

data class Show(
    val id: Int,
    val name: String,
    val image: String,
    val summary: String,
    val schedule: String,
    val genre: String): Serializable



fun ShowDTO.mapTo(): Show {
    return Show(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.image?.medium ?: "",
        summary = this.summary ?: "",
        schedule = this.schedule?.time ?: "",
        genre = this.genres?.joinToString(" ") ?: ""
    )
}