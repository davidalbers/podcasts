package com.dalbers.podcastexplorer.dagger;

import com.dalbers.podcastexplorer.search.SearchPresenter;

import com.dalbers.podcastexplorer.contracts.SearchContract;
import com.dalbers.podcastexplorer.data.PodcastMediaPlayer;

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
