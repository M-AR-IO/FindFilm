package my.mobile.findfilm.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET(ApiConst.MOVIE_POPULAR)
    fun getFilmPopuler(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1
    ): Call<GetFilmResponse>

    @GET(ApiConst.SEARCH_MOVIE)
    fun searchFilm(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1,
        @Query(ApiConst.QUERY) query: String = ""
        ): Call<GetFilmResponse>

    @GET(ApiConst.MOVIE_PLAYNOW)
    fun getFilmPlayNow(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1
    ): Call<GetFilmResponse>

    @GET(ApiConst.MOVIE)
    fun getFilm(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1
    ): Call<GetFilmResponse>

    // TV

    @GET(ApiConst.SEARCH_TV)
    fun searchTV(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1,
        @Query(ApiConst.QUERY) query: String = ""
    ): Call<GetTVResponse>

    @GET(ApiConst.TV_PLAYNOW)
    fun getTVPlayNow(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1
    ): Call<GetTVResponse>

    @GET(ApiConst.TV)
    fun getTV(
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US,
        @Query(ApiConst.PAGE) page: Int = 1
    ): Call<GetTVResponse>

    // Trailer

    @GET(ApiConst.TV_VIDEO)
    fun getTvTrailer(
        @Path("id") id: Long = 0,
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US
    ): Call<GetTrailerResponse>
    @GET(ApiConst.MOVIE_VIDEO)
    fun getFilmTrailer(
        @Path("id") id: Long = 0,
        @Query(ApiConst.API_KEY) api_key: String = ApiConst.KEY,
        @Query(ApiConst.LANGUAGE) language: String = ApiConst.LANGUAGE_EN_US
    ): Call<GetTrailerResponse>
}