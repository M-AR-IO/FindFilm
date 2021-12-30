package my.mobile.findfilm.data

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Television (
    @SerializedName("id") @PrimaryKey open var id: Long = 0,
    @SerializedName("name") @Required open var name: String = "",
    @SerializedName("original_name") open var originalName: String = "",
    @SerializedName("vote_average") open var vote_average: Float = 0f,
    @SerializedName("poster_path") @Required open var poster_path: String = "",
    @SerializedName("backdrop_path") open var backdrop_path: String = "",
    @SerializedName("first_air_date") @Required open var realise_date: String = "",
    @SerializedName("popularity") open var popularity: Float = 0f,
    @SerializedName("overview") @Required open var overview: String = ""
) : RealmObject()
