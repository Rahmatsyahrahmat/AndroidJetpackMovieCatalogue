package com.rahmatsyah.moviecatalogue.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.ui.detail.movie.DetailMovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MovieEntity> movies = new ArrayList<>();

    private final static String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setListMovie(ArrayList<MovieEntity> movieEntities){
        movies = movieEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView poster, posterBackground;
        private TextView judul;

        ViewHolder(@NonNull View view) {
            super(view);
            poster = view.findViewById(R.id.ivPosterItem);
            posterBackground = view.findViewById(R.id.ivPosterBackgroundItem);
            judul = view.findViewById(R.id.tvTitleItem);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID,movies.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }

        void bind(MovieEntity movie) {
            Glide.with(context).load(BASE_POSTER+movie.getPosterPath()).into(poster);
            Glide.with(context).load(BASE_POSTER+movie.getPosterPath()).into(posterBackground);
            judul.setText(movie.getTitle());
        }
    }
}
