package my.mobile.findfilm.data

import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import io.realm.annotations.Required
import java.io.Serializable

open class Film(
    @SerializedName("id") @PrimaryKey open var id: Long = 0,
    @SerializedName("title") @Required open var title: String = "",
    @SerializedName("original_title") open var originalName: String = "",
    @SerializedName("overview") @Required open var overview: String = "",
    @SerializedName("poster_path") @Required open var poster_path: String = "",
    @SerializedName("backdrop_path") open var backdrop_path: String = "",
    @SerializedName("vote_average") open var rating: Float = 0f,
    @SerializedName("release_date") @Required open var release_date: String = "",
    @SerializedName("popularity")  open var popularity: Float = 0f
) : RealmObject(), Serializable
