package Dagger;

import android.media.MediaPlayer;

import javax.inject.Singleton;

import Contracts.Contract;
import Data.PodcastMediaPlayer;
import dagger.Component;


/**
 * Container for data module
 */
@Component(modules = {DataModule.class})
@Singleton
public interface DataComponent {
    Contract.Model model();
    PodcastMediaPlayer mediaPlayer();
}

