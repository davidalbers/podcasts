package com.dalbers.podcastexplorer.Search;

import java.util.List;

import javax.inject.Inject;

import Contracts.SearchContract;
import Data.PodcastMediaPlayer;
import Data.Podcast;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class SearchPresenter implements SearchContract.Presenter {
    @Inject public PodcastMediaPlayer podcastMediaPlayer;
    @Inject public SearchContract.Model podcastGetter;
    private boolean playing = false;
    @Inject public SearchContract.View view;

    public SearchPresenter(PodcastMediaPlayer podcastMediaPlayer, SearchContract.Model podcastGetter, SearchContract.View view) {
        this.podcastMediaPlayer = podcastMediaPlayer;
        this.podcastGetter = podcastGetter;
        podcastGetter.setPresenter(this);
        this.view = view;
    }
    @Override
    public void searchPodcasts(String searchTerm) {
        podcastGetter.searchPodcasts(searchTerm);
    }

    @Override
    public void playPause(int id) {
        if(!playing) {
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
