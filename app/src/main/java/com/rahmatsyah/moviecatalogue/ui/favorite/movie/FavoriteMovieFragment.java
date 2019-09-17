package com.rahmatsyah.moviecatalogue.ui.favorite.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel;
import com.rahmatsyah.moviecatalogue.ui.movie.MovieAdapter;
import com.rahmatsyah.moviecatalogue.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MovieAdapter adapter;

    private FavoriteMovieViewModel viewModel;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favoriteMovieList);
        progressBar = view.findViewById(R.id.favoriteMovieProgress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            progressBar.setVisibility(View.VISIBLE);

            viewModel = obtainViewModel(this);

            adapter = new MovieAdapter(getContext());

            viewModel.getBookmarkedMovies().observe(getViewLifecycleOwner(), movies->{
                if (movies!=null){
                    switch (movies.status){
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.setListMovie(movies.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(),R.string.failed_receive_data,Toast.LENGTH_LONG).show();
                            break;
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            });

            recyclerView.setAdapter(adapter);
        }
    }

    @NonNull
    private static FavoriteMovieViewModel obtainViewModel(Fragment fragment){
        ViewModelFactory factory = ViewModelFactory.getInstance(fragment.getActivity().getApplication());
        return ViewModelProviders.of(fragment,factory).get(FavoriteMovieViewModel.class);
    }
}
