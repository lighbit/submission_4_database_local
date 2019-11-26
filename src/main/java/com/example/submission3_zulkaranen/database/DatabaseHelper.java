package com.example.submission3_zulkaranen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.submission3_zulkaranen.database.DatabaseContract.MovieFavColumns.MOVIE_TABLE_NAME;
import static com.example.submission3_zulkaranen.database.DatabaseContract.TVFavColumns.TV_TABLE_NAME;

/**
 * @author zulkarnaen
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "submission_4_zulkarnaen";
    private static final int DATABASE_VERSION = 1;


    private static final String SQL_CREATE_TABLE_MOVIE_FAVORITE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            MOVIE_TABLE_NAME,
            DatabaseContract.MovieFavColumns._ID,
            DatabaseContract.MovieFavColumns.MOVIE_TITLE,
            DatabaseContract.MovieFavColumns.MOVIE_VOTE_AVARAGE,
            DatabaseContract.MovieFavColumns.MOVIE_VOTE_COUNT,
            DatabaseContract.MovieFavColumns.MOVIE_FIRST_AIR_DATE,
            DatabaseContract.MovieFavColumns.MOVIE_OVERVIEW,
            DatabaseContract.MovieFavColumns.MOVIE_PHOTO
    );

    private static final String SQL_CREATE_TABLE_TV_SHOW_FAVORITE = String.format(
            " CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TV_TABLE_NAME,
            DatabaseContract.TVFavColumns._ID,
            DatabaseContract.TVFavColumns.TV_TITLE,
            DatabaseContract.TVFavColumns.TV_VOTE_AVARAGE,
            DatabaseContract.TVFavColumns.TV_VOTE_COUNT,
            DatabaseContract.TVFavColumns.TV_FIRST_AIR_DATE,
            DatabaseContract.TVFavColumns.TV_OVERVIEW,
            DatabaseContract.TVFavColumns.TV_PHOTO
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE_FAVORITE);
        db.execSQL(SQL_CREATE_TABLE_TV_SHOW_FAVORITE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TV_TABLE_NAME);
        onCreate(db);
    }
}
