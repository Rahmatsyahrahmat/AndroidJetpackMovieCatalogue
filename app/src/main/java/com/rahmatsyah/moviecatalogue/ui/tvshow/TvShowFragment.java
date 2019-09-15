package com.rahmatsyah.moviecatalogue.ui.tvshow;


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

import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private TvShowAdapter tvShowAdapter;

    private TvShowViewModel tvShowViewModel;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.tvShowList);
        progressBar = view.findViewById(R.id.tvShowProgress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            progressBar.setVisibility(View.VISIBLE);
            tvShowViewModel = obtainViewModel(getActivity());

            tvShowAdapter = new TvShowAdapter(getActivity());

            tvShowViewModel.getTvShows().observe(this, tvShowEntities -> {
                progressBar.setVisibility(View.GONE);
                tvShowAdapter.setListTvShows(tvShowEntities);
                tvShowAdapter.notifyDataSetChanged();
            });

            recyclerView.setAdapter(tvShowAdapter);
        }
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity){
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity,factory).get(TvShowViewModel.class);
    }
}
