package com.anagualter.popularmovies;


public final class Constants {
    public class Api {
        public static final String SCHEME = "https";
        public static final String BASE_API_URL = "api.themoviedb.org";
        public static final String API_VERSION = "3";
        public static final String API_POPULAR_MOVIES= "movie/popular";
        public static final String API_HIGHEST_RATED= "discover/movie";


        public static final String API_SORT_BY_PARAM = "sort_by";
        public static final String API_SORT_BY_HIGHEST = "vote_average.desc";
        public static final String API_KEY_PARAM = "api_key";
        public static final String API_KEY = "YOUR_API_KEY";

        public static final String KEY_SORT_POPULAR = "popular";
        public static final String KEY_SORT_HIGHEST_RATED = "highest";

        public static final String BASE_IMAGE_URL = "image.tmdb.org";
        public static final String IMAGE_DEFAULT_SIZE = "w185";
        public static final String IMAGE_MEDIUM_SIZE = "w342";
        public static final String IMAGE_LARGE_SIZE = "w780";
        public static final String IMAGE_BACKDROP_SIZE = "w500";

        public static final String MOVIE_ID = "id";
        public static final String MOVIE_POSTER_PATH = "poster_path";
        public static final String MOVIE_BACKDROP_PATH = "backdrop_path";
        public static final String MOVIE_TITLE = "original_title";
        public static final String MOVIE_OVERVIEW = "overview";
        public static final String MOVIE_RELEASE_DATE = "release_date";
        public static final String MOVIE_VOTE_AVERAGE = "vote_average";
    }

}
