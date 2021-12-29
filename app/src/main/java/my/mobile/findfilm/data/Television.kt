package my.mobile.findfilm.data

import com.google.gson.annotations.SerializedName

data class Television (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("first_air_date") val realise_date: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("overview") val overview: String
)
