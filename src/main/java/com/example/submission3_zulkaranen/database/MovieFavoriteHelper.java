package com.example.submission3_zulkaranen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.submission3_zulkaranen.model.Movies;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_FIRST_AIR_DATE;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_OVERVIEW;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_PHOTO;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_TABLE_NAME;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_TITLE;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_VOTE_AVARAGE;
import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_VOTE_COUNT;
import static com.example.submission3_zulkaranen.database.DatabaseContract.TVFavColumns.TV_POPULARITY;

/**
 * @author zulkarnaen
 */
public class MovieFavoriteHelper {

    private static final String DATABASE_TABLE = MOVIE_TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static MovieFavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieFavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MovieFavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieFavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }


    public ArrayList<Movies> getAllMoviesFav() {
        ArrayList<Movies> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Movies movieFavorite;
        if (cursor.getCount() > 0) {
            do {
                movieFavorite = new Movies();
                movieFavorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movieFavorite.setOriginal_title(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_TITLE)));
                movieFavorite.setVote_average(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_VOTE_AVARAGE)));
                movieFavorite.setVote_count(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_VOTE_COUNT)));
                movieFavorite.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_FIRST_AIR_DATE)));
                movieFavorite.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_OVERVIEW)));
                movieFavorite.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_PHOTO)));

                arrayList.add(movieFavorite);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movies movieFavorite) {

        try {
            ContentValues args = new ContentValues();
            args.put(MOVIE_TITLE, movieFavorite.getOriginal_title());
            args.put(MOVIE_VOTE_AVARAGE, movieFavorite.getVote_average());
            args.put(MOVIE_VOTE_COUNT, movieFavorite.getVote_count());
            args.put(MOVIE_FIRST_AIR_DATE, movieFavorite.getRelease_date());
            args.put(MOVIE_OVERVIEW, movieFavorite.getOverview());
            args.put(MOVIE_PHOTO, movieFavorite.getPoster_path());

            return database.insert(DATABASE_TABLE, null, args);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    public int deleteMovie(int id) {
        return database.delete(MOVIE_TABLE_NAME, _ID + " = '" + id + "'", null);
    }

}
