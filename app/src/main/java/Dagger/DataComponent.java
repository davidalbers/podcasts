package Dagger;

import javax.inject.Singleton;

import Contracts.SearchContract;
import Data.PodcastMediaPlayer;
import dagger.Component;


/**
 * Container for data module
 */
@Component(modules = {DataModule.class})
@Singleton
public interface DataComponent {
    SearchContract.Model model();
    PodcastMediaPlayer mediaPlayer();
}

