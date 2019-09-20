package com.rahmatsyah.moviecatalogue.ui.favorite.tvshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity;

import java.util.Objects;

public class FavoriteTvShowPagedAdapter extends PagedListAdapter<TvShowEntity,FavoriteTvShowPagedAdapter.ViewHolder> {

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<TvShowEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    private Context context;
    private final static String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    FavoriteTvShowPagedAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, Objects.requireNonNull(getItem(getAdapterPosition())).getId());
            context.startActivity(intent);
        }

        void bind(TvShowEntity tvShow) {
            Glide.with(context).load(BASE_POSTER+tvShow.getPosterPath()).into(poster);
            Glide.with(context).load(BASE_POSTER+tvShow.getPosterPath()).into(posterBackground);
            judul.setText(tvShow.getName());
        }
    }

}
