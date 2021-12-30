package my.mobile.findfilm.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Trailer(
    @SerializedName("key") var key: String = "",
    @SerializedName("type") var type: String = ""
): Serializable