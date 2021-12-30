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
        realm.beginTransaction()
        realm.copyToRealm(film)
        realm.commitTransaction()
    }
    fun addTvFav(television: Television){
        realm.beginTransaction()
        realm.copyToRealm(television)
        realm.commitTransaction()
    }
    fun deleteFilmFav(film: Film){
        realm.beginTransaction()
        film.deleteFromRealm()
        realm.commitTransaction()
    }
    fun deleteTvFav(television: Television){
        realm.beginTransaction()
        television.deleteFromRealm()
        realm.commitTransaction()
    }
}