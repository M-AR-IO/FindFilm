package my.mobile.findfilm.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.io.Serializable

open class User (
    @PrimaryKey var userId: String = "",
    var username: String = "",
    @Required var password: String = ""
): RealmObject(), Serializable