package my.mobile.findfilm.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmResults
import my.mobile.findfilm.data.Film
import my.mobile.findfilm.data.Television

class RealmHelper(context: Context) {
    private val realm: Realm
    private lateinit var films: RealmResults<Film>
    private lateinit var tvs: RealmResults<Television>

    init {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    fun getAllFilmFav(): List<Film>{
        val data = mutableListOf<Film>()
        films = realm.where(Film::class.java).findAll()

        films.forEach{
            data.add(it)
        }
        return data
    }
    fun getAllTvFav(): List<Television>{
        val data = mutableListOf<Television>()
        tvs = realm.where(Television::class.java).findAll()

        tvs.forEach{
            data.add(it)
        }
        return data
    }
    fun addFilmFav(film: Film){
        realm.executeTransaction {
            it.copyToRealmOrUpdate(film)
        }
    }
    fun addTvFav(television: Television){
        realm.executeTransaction {
            it.copyToRealmOrUpdate(television)
        }
    }
    fun deleteFilmFav(film: Film){
        val flm: Film? = realm.where(Film::class.java).equalTo(Film::id.name,film.id).findFirst()
        realm.executeTransaction {
            flm?.deleteFromRealm()
        }
    }
    fun deleteTvFav(television: Television){
        val tv: Television? = realm.where(Television::class.java).equalTo(Television::id.name,television.id).findFirst()
        realm.executeTransaction {
            tv?.deleteFromRealm()
        }
    }
    fun isFilmFav(film: Film): Boolean = realm.where(Film::class.java).equalTo(Film::id.name,film.id).findFirst()?.isValid ?: false
    fun isTvFav(television: Television): Boolean = realm.where(Television::class.java).equalTo(Television::id.name,television.id).findFirst()?.isValid ?: false
}