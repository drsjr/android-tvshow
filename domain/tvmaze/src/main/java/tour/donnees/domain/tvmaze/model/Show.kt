package tour.donnees.domain.tvmaze.model

import android.os.Parcel
import android.os.Parcelable
import tour.donnees.data.tvmaze.datasource.remote.dto.show.ShowDTO

data class Show(
    val id: Int,
    val name: String,
    val image: String,
    val summary: String,
    val schedule: String,
    val genre: String
    ): Parcelable {

    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(image)
        dest.writeString(summary)
        dest.writeString(schedule)
        dest.writeString(genre)
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