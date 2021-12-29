package my.mobile.findfilm.api

import com.google.gson.annotations.SerializedName
import my.mobile.findfilm.data.*

data class GetTVResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val hasil: List<Television>,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("total_results") val totalResult: Int
)
