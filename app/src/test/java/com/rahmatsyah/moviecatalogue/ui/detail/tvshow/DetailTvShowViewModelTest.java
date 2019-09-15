package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTvShowViewModel detailTvShowViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private TvShowEntity dummyTvShow = FakeDataDummy.generateDummyTvShow().get(0);

    @Before
    public void setUp(){
        detailTvShowViewModel = new DetailTvShowViewModel(catalogueRepository);
    }

    @After
    public void tearDown(){}

    @Test
    public void getMovie(){
        MutableLiveData<TvShowEntity> tvShowEntityMutableLiveData = new MutableLiveData<>();
        tvShowEntityMutableLiveData.setValue(dummyTvShow);

        when(catalogueRepository.getTvShow(dummyTvShow.getId())).thenReturn(tvShowEntityMutableLiveData);

        Observer<TvShowEntity> observer = mock(Observer.class);

        detailTvShowViewModel.getTvShow(dummyTvShow.getId()).observeForever(observer);

        verify(observer).onChanged(dummyTvShow);
    }

}