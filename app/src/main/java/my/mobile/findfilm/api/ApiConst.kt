package my.mobile.findfilm.api

class ApiConst {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val KEY = "0738ac209821db2f0ba235e1803e49ec"
        const val LANGUAGE_EN_US = "en-US"
        const val SEARCH_MOVIE = "search/movie"
        const val SEARCH_TV = "search/tv";
        const val MOVIE_PLAYNOW = "movie/now_playing";
        const val MOVIE_POPULAR = "movie/popular";
        const val MOVIE = "discover/movie";
        const val TV_PLAYNOW = "tv/on_the_air";
        const val TV = "discover/tv";
        const val TV_POPULAR = "tv/popular";
        const val URLIMAGE = "https://image.tmdb.org/t/p/w780/";
        const val URLFILM = "https://www.themoviedb.org/movie/";
        const val NOTIF_DATE = "primary_release_date.gte";
        const val REALESE_DATE = "primary_release_date.lte";
        const val MOVIE_VIDEO = "movie/{id}/videos";
        const val TV_VIDEO = "tv/{id}/videos";

        const val QUERY = "query";
        const val RESULTS = "results";
        const val PAGE = "page";
        const val TOTAL_PAGES = "total_pages";
        const val TOTAL_RESULTS = "total_results";
        const val API_KEY = "api_key";
        const val LANGUAGE = "language";
    }
}