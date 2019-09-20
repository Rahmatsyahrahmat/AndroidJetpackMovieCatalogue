package com.rahmatsyah.moviecatalogue.ui.detail.tvshow;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTvShowViewModel detailTvShowViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private Resource<TvShowEntity> dummyTvShow = Resource.success(FakeDataDummy.generateDummyTvShow().get(0));

    @Before
    public void setUp(){
        detailTvShowViewModel = new DetailTvShowViewModel(catalogueRepository);
        detailTvShowViewModel.setTvShowId(dummyTvShow.data.getId());
    }

    @After
    public void tearDown(){}

    @Test
    public void getMovie(){
        MutableLiveData<Resource<TvShowEntity>> tvShowEntityMutableLiveData = new MutableLiveData<>();
        tvShowEntityMutableLiveData.setValue(dummyTvShow);

        when(catalogueRepository.getTvShow(dummyTvShow.data.getId())).thenReturn(tvShowEntityMutableLiveData);

        Observer<Resource<TvShowEntity>> observer = mock(Observer.class);

        detailTvShowViewModel.getTvShow().observeForever(observer);

        verify(observer).onChanged(dummyTvShow);
    }

}