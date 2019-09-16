package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.Objects;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW_ID = "extra_tv_show_id";
    private final static String BASE_POSTER = "https://image.tmdb.org/t/p/w342";

    private ImageView ivPoster;
    private TextView tvTitle, tvVoteAverage, tvReleaseDate, tvOverview;
    private ProgressBar progressBar;

    private DetailTvShowViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ivPoster = findViewById(R.id.ivPosterDetail);
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvVoteAverage = findViewById(R.id.tvVoteAverageDetail);
        tvReleaseDate = findViewById(R.id.tvReleaseDateDetail);
        tvOverview = findViewById(R.id.tvOverviewDetail);
        progressBar = findViewById(R.id.detailProgress);

        viewModel = obtainViewModel();
        viewModel.setTvShowId(Objects.requireNonNull(getIntent().getExtras()).getLong(EXTRA_TV_SHOW_ID));

        viewModel.getTvShow().observe(this, tvShow->{
            if (tvShow!=null){
                switch (tvShow.status){
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        assert tvShow.data != null;
                        populateTvShow(tvShow.data);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this,R.string.failed_receive_data,Toast.LENGTH_LONG).show();
                        break;
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }

    private void populateTvShow(TvShowEntity tvShowEntity){
        Glide.with(this).load(BASE_POSTER+tvShowEntity.getPosterPath()).into(ivPoster);
        tvTitle.setText(tvShowEntity.getName());
        tvReleaseDate.setText(tvShowEntity.getFirstAirDate());
        tvVoteAverage.setText(String.valueOf(tvShowEntity.getVoteAverage()));
        tvOverview.setText(tvShowEntity.getOverview());
    }

    @NonNull
    private DetailTvShowViewModel obtainViewModel(){
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this,factory).get(DetailTvShowViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu,menu);

        viewModel.getTvShow().observe(this, tvShowEntityResource -> {
            if (tvShowEntityResource!=null){
                switch (tvShowEntityResource.status){
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        boolean state = tvShowEntityResource.data.isBookmarked();
                        setBookmarkState(menu,state);
                        break;
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this,R.string.failed_receive_data,Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            viewModel.bookmark();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setBookmarkState(Menu menu, boolean state){
        MenuItem menuItem = menu.findItem(R.id.favorite);
        if (state){
            menuItem.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorite));
        }else{
            menuItem.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorite_border));
        }
    }
}
