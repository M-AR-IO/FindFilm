package my.mobile.findfilm.data

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("popularity") val popularity: Float
)
