package my.mobile.findfilm.api

import com.google.gson.annotations.SerializedName
import my.mobile.findfilm.data.Trailer

data class GetTrailerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("results") val hasil: List<Trailer>
)