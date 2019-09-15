package com.rahmatsyah.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.rahmatsyah.moviecatalogue.data.source.local.entity.MovieEntity;
import com.rahmatsyah.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.rahmatsyah.moviecatalogue.data.source.remote.RemoteRepository;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.MovieResponse;
import com.rahmatsyah.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.rahmatsyah.moviecatalogue.utils.FakeDataDummy;
import com.rahmatsyah.moviecatalogue.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CatalogueRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = Mockito.mock(RemoteRepository.class);
    private FakeCatalogueRepository catalogueRepository = new FakeCatalogueRepository(remoteRepository);

    private ArrayList<MovieResponse> movieResponses = FakeDataDummy.generateRemoteDummyMovie();
    private MovieResponse movieResponse = movieResponses.get(0);
    private ArrayList<TvShowResponse> tvShowResponses = FakeDataDummy.generateRemoteDummyTvShow();
    private TvShowResponse tvShowResponse = tvShowResponses.get(0);

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void getMovies(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
                .onAllMovieReceived(movieResponses);
            return null;
        }).when(remoteRepository).getMovies(any(RemoteRepository.LoadMoviesCallback.class));

        ArrayList<MovieEntity> result = LiveDataTestUtil.getValue(catalogueRepository.getMovies());

        verify(remoteRepository,times(1)).getMovies(any(RemoteRepository.LoadMoviesCallback.class));

        assertNotNull(result);
        assertEquals(movieResponses.size(),result.size());
    }

    @Test
    public void getTvShows(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvShowsCallback) invocation.getArguments()[0])
                    .onAllTvShowReceived(tvShowResponses);
            return null;
        }).when(remoteRepository).getTvShows(any(RemoteRepository.LoadTvShowsCallback.class));

        ArrayList<TvShowEntity> result = LiveDataTestUtil.getValue(catalogueRepository.getTvShows());

        verify(remoteRepository,times(1)).getTvShows(any(RemoteRepository.LoadTvShowsCallback.class));

        assertNotNull(result);
        assertEquals(tvShowResponses.size(),result.size());
    }

    @Test
    public void getMovie(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieCallback) invocation.getArguments()[1])
                    .onMovieReceived(movieResponse);
            return null;
        }).when(remoteRepository).getMovie(eq(movieResponse.getId()),any(RemoteRepository.LoadMovieCallback.class));

        MovieEntity result = LiveDataTestUtil.getValue(catalogueRepository.getMovie(movieResponse.getId()));

        verify(remoteRepository,times(1)).getMovie(eq(movieResponse.getId()),any(RemoteRepository.LoadMovieCallback.class));

        assertNotNull(result);
        assertEquals(movieResponse.getId(),result.getId());
    }

    @Test
    public void getTvShow(){
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvShowCallback) invocation.getArguments()[1])
                    .onTvShowReceived(tvShowResponse);
            return null;
        }).when(remoteRepository).getTvShow(eq(tvShowResponse.getId()),any(RemoteRepository.LoadTvShowCallback.class));

        TvShowEntity result = LiveDataTestUtil.getValue(catalogueRepository.getTvShow(tvShowResponse.getId()));

        verify(remoteRepository,times(1)).getTvShow(eq(tvShowResponse.getId()),any(RemoteRepository.LoadTvShowCallback.class));

        assertNotNull(result);
        assertEquals(tvShowResponse.getId(),result.getId());
    }

}