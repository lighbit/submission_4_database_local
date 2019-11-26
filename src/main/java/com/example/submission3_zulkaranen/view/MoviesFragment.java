package com.example.submission3_zulkaranen.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission3_zulkaranen.R;
import com.example.submission3_zulkaranen.model.Movies;
import com.example.submission3_zulkaranen.modelImpl.MoviesImpl;
import com.example.submission3_zulkaranen.presenter.MoviesAdapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author zulkarnaen
 */
public class MoviesFragment extends Fragment {
    private MoviesAdapter adapter;
    private ProgressBar progressBar;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new MoviesAdapter(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBarMovies);

        MoviesImpl moviesViewModel = ViewModelProviders.of(this).get(MoviesImpl.class);
        moviesViewModel.getMovies().observe(this, getMovie);
        moviesViewModel.setMovies(Objects.requireNonNull(this.getContext()));

        showLoading(true);

        return view;
    }

    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies) {
            if (movies != null) {
                adapter.setWord(getResources().getString(R.string.choose));
                adapter.setData(movies);
                adapter.isOnFavorite(false);
                progressBar.setVisibility(View.GONE);
            }


            showLoading(false);

        }
    };


    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
