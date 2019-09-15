package com.rahmatsyah.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rahmatsyah.moviecatalogue.data.source.CatalogueRepository;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel movieViewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);

    @Before
    public void setUp(){
        movieViewModel = new MovieViewModel(catalogueRepository);
    }

    @After
    public void terDown(){

    }

    @Test
    public void getMovies(){
        ArrayList<MovieEntity> dummyMovie = FakeDataDummy.generateDummyMovie();

        MutableLiveData<ArrayList<MovieEntity>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(dummyMovie);

        when(catalogueRepository.getMovies()).thenReturn(mutableLiveData);

        Observer<ArrayList<MovieEntity>> observer = mock(Observer.class);

        movieViewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }

}