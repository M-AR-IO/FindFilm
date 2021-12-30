package my.mobile.findfilm.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {
    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConst.BASE_URL)
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
    private fun callbackTV(
        onSuccess: (hasil: GetTVResponse) -> Unit,
        onError: () -> Unit
    ) = object: Callback<GetTVResponse> {
        override fun onResponse(call: Call<GetTVResponse>, response: Response<GetTVResponse>) = when (response.isSuccessful){
            true -> response.body()?.let {onSuccess.invoke(it)} ?: run {onError.invoke()}
            false -> onError.invoke()
        }
        override fun onFailure(call: Call<GetTVResponse>, t: Throwable) = onError.invoke()
    }
    private fun callbackTrailer(
        onSuccess: (hasil: GetTrailerResponse) -> Unit,
        onError: () -> Unit
    ) = object: Callback<GetTrailerResponse> {
        override fun onResponse(call: Call<GetTrailerResponse>, response: Response<GetTrailerResponse>) = when (response.isSuccessful){
            true -> response.body()?.let {onSuccess.invoke(it)} ?: run {onError.invoke()}
            false -> onError.invoke()
        }
        override fun onFailure(call: Call<GetTrailerResponse>, t: Throwable) = onError.invoke()
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

    // TV

    fun getTvPlayNow(
        page: Int = 1,
        onSuccess: (hasil: GetTVResponse) -> Unit,
        onError: () -> Unit
    ){
        api.getTVPlayNow(page = page)
            .enqueue(callbackTV(onSuccess,onError))
    }
    fun searchTv(
        query: String,
        page: Int = 1,
        onSuccess: (hasil: GetTVResponse) -> Unit,
        onError: () -> Unit
    ){
        api.searchTV(query = query,page = page)
            .enqueue(callbackTV(onSuccess,onError))
    }
    fun getTv(
        page: Int = 1,
        onSuccess: (hasil: GetTVResponse) -> Unit,
        onError: () -> Unit
    ){
        api.getTV(page = page)
            .enqueue(callbackTV(onSuccess,onError))
    }

    // Trailer

    fun getTvTrailer(
        id: Long,
        onSuccess: (hasil: GetTrailerResponse) -> Unit,
        onError: () -> Unit
    ){
        api.getTvTrailer(id = id)
            .enqueue(callbackTrailer(onSuccess,onError))
    }
    fun getFilmTrailer(
        id: Long,
        onSuccess: (hasil: GetTrailerResponse) -> Unit,
        onError: () -> Unit
    ){
        api.getFilmTrailer(id = id)
            .enqueue(callbackTrailer(onSuccess,onError))
    }
}