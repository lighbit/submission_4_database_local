package com.example.submission3_zulkaranen.favorit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission3_zulkaranen.R;
import com.example.submission3_zulkaranen.database.MovieFavoriteHelper;
import com.example.submission3_zulkaranen.model.Movies;
import com.example.submission3_zulkaranen.presenter.MoviesAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
/**
 * @author zulkarnaen
 */
public class MoviesFavorit extends AppCompatActivity {
    private RecyclerView rvCategory;
    private MovieFavoriteHelper movieFavoriteHelper;
    private ArrayList<Movies> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie_favorit);

        rvCategory = findViewById(R.id.rv_category_favorite);
        rvCategory.setHasFixedSize(true);

        movieFavoriteHelper = MovieFavoriteHelper.getInstance(getApplicationContext());
        movieFavoriteHelper.open();


        WeakReference<MovieFavoriteHelper> weakReference = new WeakReference<>(movieFavoriteHelper);
        ArrayList<Movies> arrayList = weakReference.get().getAllMoviesFav();

        list.addAll(arrayList);

        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        MoviesAdapter movieAdapter = new MoviesAdapter(this);
        movieAdapter.setWord(getResources().getString(R.string.choose));
        movieAdapter.setData(list);
        movieAdapter.isOnFavorite(true);
        rvCategory.setAdapter(movieAdapter);

    }
}
