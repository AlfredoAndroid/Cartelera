package mx.test.cartelera.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index("id")],
    primaryKeys = ["id"])
data class Movie(
    @field:SerializedName("id") val id: Integer,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("poster_path") val posterPath: String?,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("vote_count") val voteCount: Integer,
    @field:SerializedName("vote_average") val voteAverage: Double,
    @field:SerializedName("overview") val overview: String
):Parcelable {
    val rating: String
        get() = "${(voteAverage * 10).toDouble().toInt()} Rating" //voteAverage.toString().plus(" Rating")
}