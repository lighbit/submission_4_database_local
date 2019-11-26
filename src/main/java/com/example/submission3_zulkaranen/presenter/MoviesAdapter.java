package com.example.submission3_zulkaranen.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.submission3_zulkaranen.R;
import com.example.submission3_zulkaranen.model.Movies;
import com.example.submission3_zulkaranen.view.MoviesDetailActivity;

import java.util.ArrayList;
/**
 * @author zulkarnaen
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Movies> mData = new ArrayList<>();

    public void setData(ArrayList<Movies> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    private ArrayList<Movies> getMovies() {
        return mData;
    }

    private boolean isFav;

    public void isOnFavorite(boolean value) {
        this.isFav = value;
    }

    private String word;

    public void setWord(String word) {
        this.word = word;
    }

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies, viewGroup, false);
        return new MoviesAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder categoryViewHolder, final int i) {


        categoryViewHolder.ttvTitle.setText(getMovies().get(i).getOriginal_title());
        categoryViewHolder.tvRelease.setText(getMovies().get(i).getRelease_date());
        categoryViewHolder.tvOverview.setText(getMovies().get(i).getOverview());
        categoryViewHolder.tvVoteCount.setText(getMovies().get(i).getVote_count());

        String uri = "https://image.tmdb.org/t/p/original" + getMovies().get(i).getPoster_path();
        Glide.with(categoryViewHolder.itemView.getContext())
                .load(uri)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        categoryViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(categoryViewHolder.imgPhoto);

        categoryViewHolder.itemView.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, word + " " + getMovies().get(position).getOriginal_title(), Toast.LENGTH_SHORT).show();
                Intent move = new Intent(context, MoviesDetailActivity.class);
                Movies movie = new Movies();
                movie.setOriginal_title(getMovies().get(i).getOriginal_title());
                movie.setVote_average(getMovies().get(i).getVote_average());
                movie.setVote_count(getMovies().get(i).getVote_count());
                movie.setRelease_date(getMovies().get(i).getRelease_date());
                movie.setOverview(getMovies().get(i).getOverview());
                movie.setPoster_path(getMovies().get(i).getPoster_path());
                movie.setOnfavorites(isFav);

                move.putExtra(MoviesDetailActivity.EXTRA_MOVIE, movie);
                move.putExtra(MoviesDetailActivity.EXTRA_POSITION, getMovies().get(i).getId());

                context.startActivity(move);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView ttvTitle, tvRelease, tvOverview, tvVoteCount;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ttvTitle = itemView.findViewById(R.id.txt_name);
            tvRelease = itemView.findViewById(R.id.txt_tahun);
            tvOverview = itemView.findViewById(R.id.txt_description);
            tvVoteCount = itemView.findViewById(R.id.txt_genres);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
