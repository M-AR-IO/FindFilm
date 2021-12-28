package my.mobile.findfilm.api

import my.mobile.findfilm.data.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FilmRepository {
    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    private fun callback(
        onSuccess: (hasil: GetFilmResponse) -> Unit,
        onError: () -> Unit
    ) = object: Callback<GetFilmResponse> {
        override fun onResponse(call: Call<GetFilmResponse>, response: Response<GetFilmResponse>) = when (response.isSuccessful){
            true -> response.body()?.let {onSuccess.invoke(it)} ?: run {onError.invoke()}
            false -> onError.invoke()
        }
        override fun onFailure(call: Call<GetFilmResponse>, t: Throwable) = onError.invoke()
    }

    fun getFilmPlayNow(
        page: Int = 1,
        onSuccess: (hasil: GetFilmResponse) -> Unit,
        onError: () -> Unit
    ){
        api.getFilmPlayNow(page = page)
            .enqueue(callback(onSuccess,onError))
    }
    fun searchFilm(
        query: String,
        page: Int = 1,
        onSuccess: (hasil: GetFilmResponse) -> Unit,
        onError: () -> Unit
    ){
        api.searchFilm(query = query,page = page)
            .enqueue(callback(onSuccess,onError))
    }
    fun getFilm(
        page: Int = 1,
        onSuccess: (hasil: GetFilmResponse) -> Unit,
        onError: () -> Unit
    ){
        api.getFilm(page = page)
            .enqueue(callback(onSuccess,onError))
    }
}