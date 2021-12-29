package my.mobile.findfilm.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(API.MOVIE_POPULAR)
    fun getFilmPopuler(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1
    ): Call<GetFilmResponse>

    @GET(API.SEARCH_MOVIE)
    fun searchFilm(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1,
        @Query(API.QUERY) query: String = ""
        ): Call<GetFilmResponse>

    @GET(API.MOVIE_PLAYNOW)
    fun getFilmPlayNow(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1
    ): Call<GetFilmResponse>

    @GET(API.MOVIE)
    fun getFilm(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1
    ): Call<GetFilmResponse>

    // TV

    @GET(API.SEARCH_TV)
    fun searchTV(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1,
        @Query(API.QUERY) query: String = ""
    ): Call<GetTVResponse>

    @GET(API.TV_PLAYNOW)
    fun getTVPlayNow(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1
    ): Call<GetTVResponse>

    @GET(API.TV)
    fun getTV(
        @Query(API.API_KEY) api_key: String = API.KEY,
        @Query(API.LANGUAGE) language: String = API.LANGUAGE_EN_US,
        @Query(API.PAGE) page: Int = 1
    ): Call<GetTVResponse>
}