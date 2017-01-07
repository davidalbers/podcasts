package com.dalbers.podcastexplorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import Contracts.Contract;
import Dagger.DaggerPodcastFinder;
import Dagger.PodcastFinder;
import Dagger.PresenterModule;
import Data.Podcast;

public class MainActivity extends AppCompatActivity implements Contract.View{
    private RecyclerView podcastList;
    private PodcastListAdapter adapter;
    @Inject public Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        podcastList = (RecyclerView)findViewById(R.id.podcast_list);
        podcastList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        podcastList.setLayoutManager(layoutManager);

        adapter = new PodcastListAdapter();
        podcastList.setAdapter(adapter);

        PodcastFinder podcastFinder = DaggerPodcastFinder
                .builder()
                .presenterModule(new PresenterModule(this))
                //data has already been build by base app
                .dataComponent(((BaseApplication) getApplication()).getDataComponent())
                .build();
        podcastFinder.inject(this);
        presenter.loadPodcasts();
    }

    @Override
    public void onPodcastsLoaded(List<Podcast> podcasts) {
        adapter.setPodcasts(podcasts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showPlaying() {

    }

    @Override
    public void showPaused() {

    }
}
