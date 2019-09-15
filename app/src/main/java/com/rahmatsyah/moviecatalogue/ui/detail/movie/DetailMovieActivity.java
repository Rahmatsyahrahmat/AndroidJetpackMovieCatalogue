package com.rahmatsyah.moviecatalogue.ui.detail.movie;

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

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
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

        DetailMovieViewModel viewModel = obtainViewModel();

        viewModel.getMovie(Objects.requireNonNull(getIntent().getExtras()).getLong(EXTRA_MOVIE_ID)).observe(this, movieEntity -> {
            progressBar.setVisibility(View.GONE);
            Glide.with(this).load(BASE_POSTER+movieEntity.getPosterPath()).into(ivPoster);
            tvTitle.setText(movieEntity.getTitle());
            tvReleaseDate.setText(movieEntity.getReleaseDate());
            tvVoteAverage.setText(String.valueOf(movieEntity.getVoteAverage()));
            tvOverview.setText(movieEntity.getOverview());
        });

    }

    @NonNull
    private DetailMovieViewModel obtainViewModel(){
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(this,factory).get(DetailMovieViewModel.class);
    }
}
