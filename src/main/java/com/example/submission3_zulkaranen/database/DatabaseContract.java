package com.example.submission3_zulkaranen.database;

import android.provider.BaseColumns;

/**
 * @author zulkarnaen
 */
public class DatabaseContract {
    static final class MovieFavColumns implements BaseColumns {
        static final String MOVIE_TABLE_NAME = "movie_favorites_2";
        static final String MOVIE_TITLE = "movie_title";
        static final String MOVIE_VOTE_AVARAGE = "movie_averages";
        static final String MOVIE_VOTE_COUNT = "movie_counts";
        static final String MOVIE_FIRST_AIR_DATE = "movie_first_air_dates";
        static final String MOVIE_OVERVIEW = "movie_overview";
        static final String MOVIE_PHOTO = "movie_photo";

    }

    static final class TVFavColumns implements BaseColumns {
        static final String TV_TABLE_NAME = "tv_favorites_2";
        static final String TV_TITLE = "tv_title";
        static final String TV_POPULARITY = "tv_popularitys";
        static final String TV_VOTE_AVARAGE = "tv_averages";
        static final String TV_VOTE_COUNT = "tv_counts";
        static final String TV_FIRST_AIR_DATE = "tv_first_air_dates";
        static final String TV_OVERVIEW = "tv_overview";
        static final String TV_PHOTO = "tv_photo";


    }

}
