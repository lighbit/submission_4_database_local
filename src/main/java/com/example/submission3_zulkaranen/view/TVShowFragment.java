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
import com.example.submission3_zulkaranen.model.TvShow;
import com.example.submission3_zulkaranen.modelImpl.TvShowImpl;
import com.example.submission3_zulkaranen.presenter.TVShowAdapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author zulkarnaen
 */
public class TVShowFragment extends Fragment {

    private TVShowAdapter adapter;
    private ProgressBar progressBar;


    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new TVShowAdapter(getActivity());
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_category2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);


        progressBar = view.findViewById(R.id.progressBarTv);

        TvShowImpl tvShowViewModel = ViewModelProviders.of(this).get(TvShowImpl.class);
        tvShowViewModel.getTvShow().observe(this, getTvShow);
        tvShowViewModel.setTvShow(Objects.requireNonNull(this.getContext()));

        showLoading(true);

        return view;
    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            if (tvShows != null) {
                adapter.setWord(getResources().getString(R.string.choose));
                adapter.setTvData(tvShows);
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
