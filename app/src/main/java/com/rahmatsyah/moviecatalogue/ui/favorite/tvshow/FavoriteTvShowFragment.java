package com.rahmatsyah.moviecatalogue.ui.favorite.tvshow;


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
import com.rahmatsyah.moviecatalogue.ui.tvshow.TvShowAdapter;
import com.rahmatsyah.moviecatalogue.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private FavoriteTvShowPagedAdapter adapter;

    private FavoriteTvShowViewModel viewModel;

    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favoriteTvShowList);
        progressBar = view.findViewById(R.id.favoriteTvShowProgress);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            progressBar.setVisibility(View.VISIBLE);

            viewModel = obtainViewModel(this);

            adapter = new FavoriteTvShowPagedAdapter(getContext());

            viewModel.getBookmarkedTvShows().observe(getViewLifecycleOwner(), tvShows -> {
                if (tvShows != null) {
                    switch (tvShows.status) {
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.submitList(tvShows.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), tvShows.message, Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });

            recyclerView.setAdapter(adapter);
        }
    }


    @NonNull
    private static FavoriteTvShowViewModel obtainViewModel(Fragment fragment){
        ViewModelFactory factory = ViewModelFactory.getInstance(fragment.getActivity().getApplication());
        return ViewModelProviders.of(fragment,factory).get(FavoriteTvShowViewModel.class);
    }
}
