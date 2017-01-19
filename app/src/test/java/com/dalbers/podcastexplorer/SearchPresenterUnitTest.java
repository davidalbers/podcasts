package com.dalbers.podcastexplorer;

import com.dalbers.podcastexplorer.Search.SearchPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import Contracts.SearchContract;
import Data.PodcastMediaPlayer;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterUnitTest {
    @Mock
    SearchContract.View view;
    @Mock SearchContract.Model model;
    SearchContract.Presenter presenter;
    @Mock
    PodcastMediaPlayer mediaPlayer;

    @Before
    public void setup() {
        presenter = new SearchPresenter(mediaPlayer, model, view);
    }

    @Test
    public void verifyPlayPause() {
        presenter.playPause(1);
        verify(mediaPlayer).play();
        verify(view).showPlaying();
        presenter.playPause(1);
        verify(mediaPlayer).pause();
        verify(view).showPaused();
    }

    @Test
    public void verifyLoad() {
        presenter.searchPodcasts();
        verify(model).searchPodcasts();
    }

    @Test
    public void verifyOnLoad() {
        presenter.onPodcastsLoaded(anyList());
        verify(view).onPodcastsLoaded(anyList());
    }
}