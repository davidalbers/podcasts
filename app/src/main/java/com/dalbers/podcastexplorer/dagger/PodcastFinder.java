package com.dalbers.podcastexplorer.dagger;

import com.dalbers.podcastexplorer.search.SearchController;

import dagger.Component;

/**
 * All the stuff needed for the find podcasts view
 */
@AppScope
@Component(modules = PresenterModule.class,
            dependencies = DataComponent.class)
public interface PodcastFinder {
    void inject(SearchController controller);
}
