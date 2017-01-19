package Dagger;

import com.dalbers.podcastexplorer.Search.SearchPresenter;

import Contracts.SearchContract;
import Data.PodcastMediaPlayer;
import dagger.Module;
import dagger.Provides;


/**
 * Provide the presenter
 */
@Module
public class PresenterModule {

    SearchContract.View view;

    @Provides
    SearchContract.Presenter providePresenter(PodcastMediaPlayer mediaPlayer, SearchContract.Model model) {
        return new SearchPresenter(mediaPlayer,model,view);
    }

    public PresenterModule(SearchContract.View view) {
        this.view = view;
    }
}
