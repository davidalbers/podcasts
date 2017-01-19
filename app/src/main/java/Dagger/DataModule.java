package Dagger;

import javax.inject.Singleton;

import Contracts.SearchContract;
import Data.FakePodcastMediaPlayer;
import Data.GetPodcasts;
import Data.PodcastMediaPlayer;
import dagger.Module;
import dagger.Provides;


/**
 * Some singletons for sending/receiving data and playing podcasts
 */
@Module
public class DataModule {
    @Provides @Singleton SearchContract.Model provideModel() {
        return new GetPodcasts();
    }

    @Provides @Singleton PodcastMediaPlayer provideMediaPlayer() {
        return new FakePodcastMediaPlayer();
    }
}
