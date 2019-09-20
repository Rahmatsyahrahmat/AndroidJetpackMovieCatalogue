package com.rahmatsyah.moviecatalogue.ui.detail.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;
import com.rahmatsyah.moviecatalogue.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel detailMovieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private Resource<MovieEntity> dummyMovie = Resource.success(FakeDataDummy.generateDummyMovie().get(0));

    @Before
    public void setUp(){
        detailMovieViewModel = new DetailMovieViewModel(catalogueRepository);
        detailMovieViewModel.setMovieId(dummyMovie.data.getId());
    }

    @After
    public void tearDown(){}

    @Test
    public void getMovie(){
        MutableLiveData<Resource<MovieEntity>> movieEntityMutableLiveData = new MutableLiveData<>();
        movieEntityMutableLiveData.setValue(dummyMovie);

        when(catalogueRepository.getMovie(dummyMovie.data.getId())).thenReturn(movieEntityMutableLiveData);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);

        detailMovieViewModel.getMovie().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}