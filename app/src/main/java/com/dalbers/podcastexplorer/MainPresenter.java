package com.dalbers.podcastexplorer;

import java.util.List;

import Contracts.Contract;
import Contracts.PodcastMediaPlayer;
import Data.Podcast;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class MainPresenter implements Contract.Presenter {
    private PodcastMediaPlayer podcastMediaPlayer;
    private Contract.Model podcastGetter;
    private boolean playing = false;
    private Contract.View view;
    public MainPresenter(PodcastMediaPlayer podcastMediaPlayer, Contract.Model podcastGetter, Contract.View view) {
        this.podcastMediaPlayer = podcastMediaPlayer;
        this.podcastGetter = podcastGetter;
        podcastGetter.setPresenter(this);
        this.view = view;
    }
    @Override
    public void loadPodcasts() {
        podcastGetter.loadPodcasts();
    }

    @Override
    public void playPause(int id) {
        if(playing) {
            podcastMediaPlayer.play();
            view.showPlaying();
        }
        else {
            podcastMediaPlayer.pause();
            view.showPaused();
        }
        playing = !playing;
    }

    @Override
    public void onPodcastsLoaded(List<Podcast> podcasts) {
        view.onPodcastsLoaded(podcasts);
    }
}
