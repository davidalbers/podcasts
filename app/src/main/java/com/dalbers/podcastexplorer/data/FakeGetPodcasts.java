package com.dalbers.podcastexplorer.data;


import android.os.Handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.dalbers.podcastexplorer.contracts.SearchContract;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class FakeGetPodcasts implements SearchContract.Model {
    private SearchContract.Presenter presenter;
    private ArrayList<Podcast> podcasts;
    public FakeGetPodcasts() {
        podcasts = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i * -1);
            Date date = cal.getTime();
            podcasts.add(new Podcast("Name" + podcasts.size(), "Title" + podcasts.size(), "Lorem ipsum" + podcasts.size(), date));
        }
    }

    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void searchPodcasts(String searchTerm) {
        Handler handler = new Handler();
        handler.postDelayed(waitToSendBackData, 100);
    }

    final Runnable waitToSendBackData = new Runnable() {
        public void run() {
            presenter.onPodcastsLoaded(podcasts);
        }
    };

}
