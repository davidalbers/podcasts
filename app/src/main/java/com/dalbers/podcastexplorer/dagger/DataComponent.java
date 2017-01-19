package com.dalbers.podcastexplorer.dagger;

import javax.inject.Singleton;

import com.dalbers.podcastexplorer.contracts.SearchContract;
import com.dalbers.podcastexplorer.data.PodcastMediaPlayer;

import dagger.Component;


/**
 * Container for com.dalbers.podcastexplorer.data module
 */
@Component(modules = {DataModule.class})
@Singleton
public interface DataComponent {
    SearchContract.Model model();
    PodcastMediaPlayer mediaPlayer();
}

