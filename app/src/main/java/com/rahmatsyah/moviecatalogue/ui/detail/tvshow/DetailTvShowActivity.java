package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.Objects;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW_ID = "extra_tv_show_id";
    private final static String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView ivPoster = findViewById(R.id.ivPosterDetail);
        TextView tvTitle = findViewById(R.id.tvTitleDetail);
        TextView tvVoteAverage = findViewById(R.id.tvVoteAverageDetail);
        TextView tvReleaseDate = findViewById(R.id.tvReleaseDateDetail);
        TextView tvOverview = findViewById(R.id.tvOverviewDetail);
        ProgressBar progressBar = findViewById(R.id.detailProgress);

        DetailTvShowViewModel detailTvShowViewModel = obtainViewModel();

        detailTvShowViewModel.getTvShow(Objects.requireNonNull(getIntent().getExtras()).getLong(EXTRA_TV_SHOW_ID)).observe(this, tvShowEntity -> {
            progressBar.setVisibility(View.GONE);
            Glide.with(this).load(BASE_POSTER+tvShowEntity.getPosterPath()).into(ivPoster);
            tvTitle.setText(tvShowEntity.getName());
            tvReleaseDate.setText(tvShowEntity.getFirstAirDate());
            tvVoteAverage.setText(String.valueOf(tvShowEntity.getVoteAverage()));
            tvOverview.setText(tvShowEntity.getOverview());
        });




    }

    @NonNull
    private DetailTvShowViewModel obtainViewModel(){
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(this,factory).get(DetailTvShowViewModel.class);
    }
}