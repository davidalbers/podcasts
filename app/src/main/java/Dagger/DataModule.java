package Dagger;

import javax.inject.Singleton;

import Contracts.Contract;
import Data.FakeGetPodcasts;
import Data.FakePodcastMediaPlayer;
import Data.PodcastMediaPlayer;
import dagger.Module;
import dagger.Provides;


/**
 * Some singletons for sending/receiving data and playing podcasts
 */
@Module
public class DataModule {
    @Provides @Singleton Contract.Model provideModel() {
        return new FakeGetPodcasts();
    }

    @Provides @Singleton PodcastMediaPlayer provideMediaPlayer() {
        return new FakePodcastMediaPlayer();
    }
}
