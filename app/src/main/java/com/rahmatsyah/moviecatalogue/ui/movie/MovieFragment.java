package com.rahmatsyah.moviecatalogue.ui.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MovieAdapter movieAdapter;

    private MovieViewModel movieViewModel;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.movieList);
        progressBar = view.findViewById(R.id.movieProgress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            progressBar.setVisibility(View.VISIBLE);
            movieViewModel = obtainViewModel(getActivity());

            movieAdapter = new MovieAdapter(getActivity());

            movieViewModel.getMovies().observe(this,movies->{
                if (movies!=null){
                    switch (movies.status){
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            movieAdapter.setListMovie(movies.data);
                            movieAdapter.notifyDataSetChanged();
                            break;
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(),R.string.failed_receive_data,Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });

            recyclerView.setAdapter(movieAdapter);
        }
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity,factory).get(MovieViewModel.class);
    }
}
