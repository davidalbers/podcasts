package Dagger;

import com.dalbers.podcastexplorer.MainPresenter;

import Contracts.Contract;
import Data.PodcastMediaPlayer;
import dagger.Module;
import dagger.Provides;


/**
 * Provide the presenter
 */
@Module
public class PresenterModule {

    Contract.View view;

    @Provides
    Contract.Presenter providePresenter(PodcastMediaPlayer mediaPlayer, Contract.Model model) {
        return new MainPresenter(mediaPlayer,model,view);
    }

    public PresenterModule(Contract.View view) {
        this.view = view;
    }
}
