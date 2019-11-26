package com.example.submission3_zulkaranen.favorit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.submission3_zulkaranen.R;
import com.example.submission3_zulkaranen.database.TVShowFavoriteHelper;
import com.example.submission3_zulkaranen.model.TvShow;
import com.example.submission3_zulkaranen.presenter.TVShowAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author zulkarnaen
 */
public class TvShowFavorit extends AppCompatActivity {
    private RecyclerView rvCategory;
    private TVShowFavoriteHelper tvShowFavoriteHelper;
    private ArrayList<TvShow> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie_favorit);

        rvCategory = findViewById(R.id.rv_category_favorite);
        rvCategory.setHasFixedSize(true);

        tvShowFavoriteHelper = TVShowFavoriteHelper.getInstance(getApplicationContext());
        tvShowFavoriteHelper.open();

        WeakReference<TVShowFavoriteHelper> weakReference = new WeakReference<>(tvShowFavoriteHelper);
        ArrayList<TvShow> arrayList = weakReference.get().getAllTvFavorite();

        list.addAll(arrayList);

        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        TVShowAdapter tvAdapter = new TVShowAdapter(this);
        tvAdapter.setWord(getResources().getString(R.string.choose));
        tvAdapter.setTvData(list);
        tvAdapter.isOnFavorite(true);
        rvCategory.setAdapter(tvAdapter);

    }
}
