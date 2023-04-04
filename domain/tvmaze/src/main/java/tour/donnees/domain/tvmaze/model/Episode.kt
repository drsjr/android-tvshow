package tour.donnees.domain.tvmaze.model

import android.os.Parcel
import android.os.Parcelable
import tour.donnees.data.tvmaze.datasource.remote.dto.episode.EpisodeDTO

data class Episode(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val image: String
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeInt(number)
        dest.writeInt(season)
        dest.writeString(summary)
        dest.writeString(image)
    }

    companion object CREATOR : Parcelable.Creator<Show> {
        override fun createFromParcel(parcel: Parcel): Show {
            return Show(parcel)
        }

        override fun newArray(size: Int): Array<Show?> {
            return arrayOfNulls(size)
        }
    }
}

fun EpisodeDTO.mapTo(): Episode {
    return Episode(
        id = this.id ?: 0,
        name = this.name ?: "",
        number = this.number ?: 0,
        season = this.season ?: 0,
        summary = this.summary ?: "",
        image = this.image?.medium ?: ""
    )
}