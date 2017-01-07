package com.dalbers.podcastexplorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import Contracts.Contract;
import Contracts.PodcastMediaPlayer;
import Data.FakeGetPodcasts;
import Data.FakePodcastMediaPlayer;
import Data.Podcast;

public class MainActivity extends AppCompatActivity implements Contract.View{
    private RecyclerView podcastList;
    private PodcastListAdapter adapter;
    private Contract.Presenter presenter;
    private PodcastMediaPlayer mediaPlayer;
    private Contract.Model podcastGetter;
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

        mediaPlayer = new FakePodcastMediaPlayer();
        podcastGetter = new FakeGetPodcasts();
        presenter = new MainPresenter(mediaPlayer,podcastGetter,this);
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
