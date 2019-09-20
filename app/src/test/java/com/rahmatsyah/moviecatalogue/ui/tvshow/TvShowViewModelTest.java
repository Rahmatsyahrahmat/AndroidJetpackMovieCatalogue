package com.rahmatsyah.moviecatalogue.ui.tvshow;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvShowViewModel tvShowViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);

    @Before
    public void setUp(){
        tvShowViewModel = new TvShowViewModel(catalogueRepository);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getTvShows() {
        Resource<List<TvShowEntity>> dummyTvShow = Resource.success(FakeDataDummy.generateDummyTvShow());

        MutableLiveData<Resource<List<TvShowEntity>>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(dummyTvShow);

        when(catalogueRepository.getTvShows()).thenReturn(mutableLiveData);

        Observer<Resource<List<TvShowEntity>>> observer = mock(Observer.class);

        tvShowViewModel.getTvShows().observeForever(observer);

        verify(observer).onChanged(dummyTvShow);
    }

}