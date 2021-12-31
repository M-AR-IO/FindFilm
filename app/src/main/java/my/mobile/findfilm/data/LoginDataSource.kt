package my.mobile.findfilm.data

import android.content.Context
import my.mobile.findfilm.data.model.LoggedInUser
import my.mobile.findfilm.data.model.User
import my.mobile.findfilm.realm.RealmHelper
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(context: Context) {

    private val realm: RealmHelper = RealmHelper(context)

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val fakeUser: LoggedInUser
            val user = realm.getUser(username,password)
            if (user != null) fakeUser = LoggedInUser(user.userId,user.username)
            else {
                fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
                realm.register(User(fakeUser.userId,username,password))
            }
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}