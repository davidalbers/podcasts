package com.dalbers.podcastexplorer.dagger;

import javax.inject.Singleton;

import com.dalbers.podcastexplorer.contracts.SearchContract;
import com.dalbers.podcastexplorer.data.FakePodcastMediaPlayer;
import com.dalbers.podcastexplorer.data.GetPodcasts;
import com.dalbers.podcastexplorer.data.PodcastMediaPlayer;

import dagger.Module;
import dagger.Provides;


/**
 * Some singletons for sending/receiving com.dalbers.podcastexplorer.data and playing podcasts
 */
@Module
public class DataModule {
    @Provides
    @Singleton SearchContract.Model provideModel() {
        return new GetPodcasts();
    }

    @Provides @Singleton PodcastMediaPlayer provideMediaPlayer() {
        return new FakePodcastMediaPlayer();
    }
}
