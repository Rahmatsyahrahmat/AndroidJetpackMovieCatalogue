package com.rahmatsyah.moviecatalogue.ui.tvshow;

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

import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TvShowEntity> tvShows = new ArrayList<>();

    private final static String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public void setListTvShows(ArrayList<TvShowEntity> tvShows){
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
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
            Intent intent = new Intent(context, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID,tvShows.get(getAdapterPosition()).getId());
            context.startActivity(intent);
        }

        void bind(TvShowEntity tvShow) {
            Glide.with(context).load(BASE_POSTER+tvShow.getPosterPath()).into(poster);
            Glide.with(context).load(BASE_POSTER+tvShow.getPosterPath()).into(posterBackground);
            judul.setText(tvShow.getName());
        }
    }
}
