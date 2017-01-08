package com.dalbers.podcastexplorer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import Contracts.Contract;
import Data.PodcastMediaPlayer;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MainPresenterUnitTest {
    @Mock
    Contract.View view;
    @Mock Contract.Model model;
    Contract.Presenter presenter;
    @Mock
    PodcastMediaPlayer mediaPlayer;

    @Before
    public void setup() {
        presenter = new MainPresenter(mediaPlayer, model, view);
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
        presenter.loadPodcasts();
        verify(model).loadPodcasts();
    }

    @Test
    public void verifyOnLoad() {
        presenter.onPodcastsLoaded(anyList());
        verify(view).onPodcastsLoaded(anyList());
    }
}