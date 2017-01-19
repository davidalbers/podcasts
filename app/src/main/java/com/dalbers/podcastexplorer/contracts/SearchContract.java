package com.dalbers.podcastexplorer.contracts;

import java.util.List;

import com.dalbers.podcastexplorer.data.Podcast;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class SearchContract {

    public interface View {
        void onPodcastsLoaded(List<Podcast> podcasts);
        void showPlaying();
        void showPaused();
    }

    public interface Presenter {
        void searchPodcasts(String searchTerm);
        void playPause(int id);
        void onPodcastsLoaded(List<Podcast> podcasts);
    }

    public interface Model {
        void searchPodcasts(String searchTerm);
        void setPresenter(SearchContract.Presenter presenter);
    }
}
